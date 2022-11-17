package ctrlpkg;

import datalpkg.Categoria;
import datalpkg.Riferimento;
import datalpkg.Utente;
import guipkg.LoginFrame;
import guipkg.MainFrame;
import guipkg.RegisterFrame;
import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
	UtenteDAO uDAO;
	RiferimentoDAO rDAO;
	CategoriaDAO cDAO;
	Utente loginUser;
	DBConnection dbc;
	LoginFrame lf;
	MainFrame mf;
	RegisterFrame rf;

	public Controller() throws IOException {
		lf = new LoginFrame(this);
		lf.setVisible(true);
		rDAO = RiferimentoDAO.getInstance();
		uDAO = UtenteDAO.getInstance();
		cDAO = CategoriaDAO.getInstance();
	}

	public static void main(String[] args) {
		try {
			new Controller();
		} catch (Throwable a) {
			JOptionPane.showMessageDialog(null, "Errore: " + a.getMessage());
		}
	}

	public void backToLogin() {
		rf.setVisible(false);
		lf.setVisible(true);
	}

	public void register() {
		try {
			dbc = DBConnection.getInstance();
			dbc.getConnection();
			rf = new RegisterFrame(this, uDAO.getNextId());
			lf.setVisible(false);
			rf.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	public void updateRiferimento(int id_rif, String titolo, String descr, String URL, Integer doi, Boolean isDigital, Date data, String tipo) {
		try {
			Riferimento r = new Riferimento(id_rif, titolo, data, tipo, URL, doi, isDigital, descr);
			rDAO.update(r);
			JOptionPane.showMessageDialog(null, "Riferimento aggiornato correttamente!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	public void creaUtente(int user_ID, String nome, String cognome) {
		try {
			Utente u = new Utente(nome, cognome, user_ID, new Date(System.currentTimeMillis()), null);
			uDAO.insert(u);
			JOptionPane.showMessageDialog(null, "Utente creato!");
			backToLogin();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public void deleteRiferimento(int user_ID, String nomeRif) {
		try {
				rDAO.delete(RetrieveRiferimento(nomeRif),user_ID);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public int retrieveCodiceCategoria(String nomeCat) throws SQLException {
		return cDAO.getByName(nomeCat).getId_Cat();
	}

	public void creaRiferimento(String nomeRif, Date data, String tipo, String URL, Integer DOI, boolean dig,
			String descr_rif, ArrayList<Integer> autori, ArrayList<Integer> categorie,
			ArrayList<Integer> citazioni) {
		try {
			int id_Rif = rDAO.getNextId();
			if (!autori.contains(retrieveID()))
				autori.add(0, retrieveID());
			Riferimento r = new Riferimento(id_Rif, nomeRif, data, tipo, URL, DOI, dig, descr_rif);
			rDAO.insert(r,autori,categorie,citazioni);
			JOptionPane.showMessageDialog(null, "Riferimento creato!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public void login(String user_ID) throws IOException {
		try {
			dbc = DBConnection.getInstance();
			dbc.getConnection();
			loginUser = uDAO.get(Integer.parseInt(user_ID));
			if (Objects.isNull(loginUser))
				JOptionPane.showMessageDialog(null, "Login fallito");
			else {
				JOptionPane.showMessageDialog(null, "Login riuscito");
				mf = new MainFrame(this);
				lf.setVisible(false);
				mf.setVisible(true);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public void logout() {
		try {
			dbc.closeConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
		mf.setVisible(false);
		lf.emptyFields();
		lf.setVisible(true);
	}

	public String retrieveNome() {
		return loginUser.getNome();
	}

	public String retrieveCognome() {
		return loginUser.getCognome();
	}

	public int retrieveID() {
		return loginUser.getUser_ID();
	}

	public List<Riferimento> retrieveRiferimenti(int ID) {
		try {
			return rDAO.getByUser(ID);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Riferimento fillCategorie(Riferimento r) {
		try {
			r.setCategorie(cDAO.getByRif(r));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
		return r;
	}

	public Riferimento fillAutori(Riferimento r) {
		try {
			r.setAutori(uDAO.getByRif(r));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
		return r;
	}

	public List<Riferimento> retrieveCitazioni(Riferimento r) {
		try {
			return rDAO.getByRif(r);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Object[][] RicercaToObjectMatrix(String testo, String categoria, boolean[] tipi, String filtro) {

		try {
			List<Riferimento> risultati = rDAO.getBySearch(testo, categoria, tipi, filtro);
			return RiferimentiToObjectMatrix(risultati, risultati.size());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public void creaCategoria(String nomeCat, String nomeSuperCat, int user_ID) {
		try {
			Categoria c;
			if (Objects.isNull(nomeSuperCat))
				c = new Categoria(cDAO.getNextId(), nomeCat, user_ID);
			else {
				c = new Categoria(cDAO.getNextId(), nomeCat, user_ID, retrieveCodiceCategoria(nomeSuperCat));
			}

			cDAO.insert(c);
			JOptionPane.showMessageDialog(null, "Categoria creata!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public String[] getRiferimenti() {
		try {
			List<Riferimento> list = rDAO.getAll();
			ArrayList<String> strings = new ArrayList<>();
			for(Riferimento r: list)
			{
				strings.add(r.getTitolo());
			}
			return strings.toArray(new String[strings.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String[] getRiferimentiByUser() {
		try {
			List<Riferimento> arrRiferimenti = rDAO.getByUser(loginUser.getUser_ID());
			ArrayList<String> strings = new ArrayList<>();
			for(Riferimento r : arrRiferimenti)
			{
				strings.add(r.getTitolo());
			}

			return strings.toArray(new String[arrRiferimenti.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String[] getUtenti() {
		try {
			List<Utente> arrUtenti = uDAO.getAll();
			ArrayList<String> strings = new ArrayList<>();
			for(Utente u: arrUtenti)
			{
				strings.add(u.getNome() + " " + u.getCognome());
			}
			return strings.toArray(new String[arrUtenti.size()]);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Riferimento RetrieveRiferimento(String nome) {
		try {
			return rDAO.getByName(nome);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}


	public String[] getCategorie(boolean b) {
		try {
			ArrayList<String> strings = new ArrayList<>();
			if (b)
				strings.add("Qualsiasi");
			List<Categoria> list = cDAO.getAll();

			for(Categoria c : list)
			{
				strings.add(c.getNome());
			}
			return strings.toArray(new String[strings.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Object[][] RiferimentiToObjectMatrix(List<Riferimento> listaRiferimenti, int righe) {
		Object[][] data = new Object[righe][7];
		for (int i = 0; i < listaRiferimenti.size() && i < righe; i++) {
			listaRiferimenti.set(i, this.fillAutori(listaRiferimenti.get(i)));
			listaRiferimenti.set(i, this.fillCategorie(listaRiferimenti.get(i)));
		}
		for (int i = 0; i < listaRiferimenti.size() && i < righe; i++) {
			data[i][0] = listaRiferimenti.get(i).getTitolo();
			data[i][1] = listaRiferimenti.get(i).autoriToString();
			data[i][2] = listaRiferimenti.get(i).getDataCreazione();
			data[i][3] = listaRiferimenti.get(i).getURL();
			data[i][4] = listaRiferimenti.get(i).getDOI();
			data[i][5] = listaRiferimenti.get(i).categorieToString();
			data[i][6] = listaRiferimenti.get(i).getTipo();
		}
		return data;
	}

	public Object[][] CitazioniToObjectMatrix(List<Riferimento> listaRiferimenti, int righe) {
		Object[][] data = new Object[righe][8];
		int fillTemp = 0;
		for (int i = 0; i < listaRiferimenti.size() && fillTemp < righe; i++) {
			List<Riferimento> citTemp = retrieveCitazioni(listaRiferimenti.get(i));
			for (int j = 0; j < citTemp.size() && fillTemp < righe; j++) {
				citTemp.set(j, this.fillAutori(citTemp.get(j)));
				citTemp.set(j, this.fillCategorie(citTemp.get(j)));
				data[fillTemp][0] = citTemp.get(j).getTitolo();
				data[fillTemp][1] = citTemp.get(j).autoriToString();
				data[fillTemp][2] = citTemp.get(j).getDataCreazione();
				data[fillTemp][3] = citTemp.get(j).getURL();
				data[fillTemp][4] = citTemp.get(j).getDOI();
				data[fillTemp][5] = citTemp.get(j).categorieToString();
				data[fillTemp][6] = citTemp.get(j).getTipo();
				data[fillTemp][7] = listaRiferimenti.get(i).getTitolo();
				fillTemp = fillTemp + 1;
			}
		}
		return data;
	}
}
