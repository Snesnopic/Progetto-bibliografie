package ctrlpkg;

import daopkg.CategoriaDAO;
import daopkg.RiferimentoDAO;
import daopkg.UtenteDAO;
import datalpkg.Categoria;
import datalpkg.Riferimento;
import datalpkg.Utente;
import guipkg.LoginFrame;
import guipkg.MainFrame;
import guipkg.RegisterFrame;
import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
	public static final String[] emptyStringArray = new String[0];
	UtenteDAO uDAO;
	RiferimentoDAO rDAO;
	CategoriaDAO cDAO;
	Utente loginUser;
	LoginFrame lf;
	MainFrame mf;
	RegisterFrame rf;

	public Controller() {
		lf = new LoginFrame(this);
		lf.setVisible(true);
		rDAO = RiferimentoDAO.getInstance();
		uDAO = UtenteDAO.getInstance();
		cDAO = CategoriaDAO.getInstance();
	}

	public static void main(final String[] args) {
			new Controller();
	}

	public void backToLogin() {
		rf.setVisible(false);
		lf.setVisible(true);
	}

	public void register() {
		try {
			rf = new RegisterFrame(this, uDAO.getNextId());
			lf.setVisible(false);
			rf.setVisible(true);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public void updateRiferimento(final int id_rif, final String titolo, final String descr, final String URL,
								  final Integer doi, final Boolean isDigital, final Date data, final String tipo) {
		try {
			final Riferimento r = new Riferimento(id_rif, titolo, data, tipo, URL, doi, isDigital, descr);
			rDAO.update(r);
			JOptionPane.showMessageDialog(null, "Riferimento aggiornato correttamente!");
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public void creaUtente(final int user_ID, final String nome, final String cognome) {
		try {
			final Utente u = new Utente(nome, cognome, user_ID, new Date(System.currentTimeMillis()), null);
			uDAO.insert(u);
			JOptionPane.showMessageDialog(null, "Utente creato!");
			backToLogin();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public void deleteRiferimento(final int user_ID, final String nomeRif) {
		try {
				rDAO.delete(RetrieveRiferimento(nomeRif),user_ID);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public int retrieveCodiceCategoria(final String nomeCat) {
		try {
			return cDAO.getByName(nomeCat).getId_Cat();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return 0;
		}
	}

	public void creaRiferimento(final String nomeRif, final Date data, final String tipo, final String URL,
								final Integer DOI, final boolean dig, final String descr_rif, final List<Integer> autori
			,final Iterable<Integer> categorie,final Iterable<Integer> citazioni) {
		try {
			final int id_Rif = rDAO.getNextId();
			if (!autori.contains(retrieveID()))
				autori.add(0, retrieveID());
			final Riferimento r = new Riferimento(id_Rif, nomeRif, data, tipo, URL, DOI, dig, descr_rif);
			rDAO.insert(r,autori,categorie,citazioni);
			JOptionPane.showMessageDialog(null, "Riferimento creato!");
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public void login(final String user_ID) {
		try {
			loginUser = uDAO.get(Integer.parseInt(user_ID));
			if (Objects.isNull(loginUser))
				JOptionPane.showMessageDialog(null, "Login fallito");
			else {
				JOptionPane.showMessageDialog(null, "Login riuscito");
				mf = new MainFrame(this);
				lf.setVisible(false);
				mf.setVisible(true);
			}
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public void logout() {
		uDAO.closeResource();	//disconnette il database
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

	public List<Riferimento> retrieveRiferimenti(final int ID) {
		try {
			return rDAO.getByUser(ID);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public Riferimento fillCategorie(final Riferimento r) {
		try {
			r.setCategorie(cDAO.getByRif(r));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
		return r;
	}

	public Riferimento fillAutori(final Riferimento r) {
		try {
			r.setAutori(uDAO.getByRif(r));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
		return r;
	}

	public List<Riferimento> retrieveCitazioni(final Riferimento r) {
		try {
			return rDAO.getByRif(r);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public Object[][] RicercaToObjectMatrix(final String testo, final String categoria, final boolean[] tipi, final String filtro) {

		try {
			final List<Riferimento> risultati = rDAO.getBySearch(testo, categoria, tipi, filtro);
			return RiferimentiToObjectMatrix(risultati, risultati.size());
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public void creaCategoria(final String nomeCat, final String nomeSuperCat, final int user_ID) {
		try {
			final Categoria c;
			if (Objects.isNull(nomeSuperCat))
				c = new Categoria(cDAO.getNextId(), nomeCat, user_ID);
			else {
				c = new Categoria(cDAO.getNextId(), nomeCat, user_ID, retrieveCodiceCategoria(nomeSuperCat));
			}

			cDAO.insert(c);
			JOptionPane.showMessageDialog(null, "Categoria creata!");
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
		}
	}

	public String[] getRiferimenti() {
		try {
			final List<Riferimento> list = rDAO.getAll();
			final ArrayList<String> strings = new ArrayList<>(list.size());
			for(final Riferimento r: list)
			{
				strings.add(r.getTitolo());
			}
			return strings.toArray(emptyStringArray);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public String[] getRiferimentiByUser() {
		try {
			final List<Riferimento> arrRiferimenti = rDAO.getByUser(loginUser.getUser_ID());
			final ArrayList<String> strings = new ArrayList<>(arrRiferimenti.size());
			for(final Riferimento r : arrRiferimenti)
			{
				strings.add(r.getTitolo());
			}

			return strings.toArray(emptyStringArray);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public String[] getUtenti() {
		try {
			final List<Utente> arrUtenti = uDAO.getAll();
			final ArrayList<String> strings = new ArrayList<>(arrUtenti.size());
			for(final Utente u: arrUtenti)
			{
				strings.add(u.getNome() + " " + u.getCognome());
			}
			return strings.toArray(emptyStringArray);
		}
		catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public Riferimento RetrieveRiferimento(final String nome) {
		try {
			return rDAO.getByName(nome);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}


	public String[] getCategorie(final boolean b) {
		try {

			final List<Categoria> list = cDAO.getAll();

			final ArrayList<String> strings = new ArrayList<>(list.size());
			if (b)
				strings.add("Qualsiasi");
			for(final Categoria c : list)
			{
				strings.add(c.getNome());
			}
			return strings.toArray(emptyStringArray);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error:\n" + e.getMessage());
			return null;
		}
	}

	public Object[][] RiferimentiToObjectMatrix(final List<Riferimento> listaRiferimenti, final int righe) {
		final Object[][] data = new Object[righe][7];
		for (int i = 0; i < listaRiferimenti.size() && i < righe; i++) {
			listaRiferimenti.set(i, this.fillAutori(listaRiferimenti.get(i)));
			listaRiferimenti.set(i, this.fillCategorie(listaRiferimenti.get(i)));
		}
		for (int i = 0; i < listaRiferimenti.size() && i < righe; i++) {
			fillMatrixWithRiferimenti(data, i, listaRiferimenti.get(i));
		}
		return data;
	}

	private static void fillMatrixWithRiferimenti(final Object[][] data, final int i, final Riferimento riferimento) {
		data[i][0] = riferimento.getTitolo();
		data[i][1] = riferimento.autoriToString();
		data[i][2] = riferimento.getDataCreazione();
		data[i][3] = riferimento.getURL();
		data[i][4] = riferimento.getDOI();
		data[i][5] = riferimento.categorieToString();
		data[i][6] = riferimento.getTipo();
	}

	public Object[][] CitazioniToObjectMatrix(final List<Riferimento> listaRiferimenti, final int righe) {
		final Object[][] data = new Object[righe][8];
		int fillTemp = 0;
		for (int i = 0; i < listaRiferimenti.size() && fillTemp < righe; i++) {
			final List<Riferimento> citTemp = retrieveCitazioni(listaRiferimenti.get(i));
			for (int j = 0; j < citTemp.size() && fillTemp < righe; j++) {
				citTemp.set(j, this.fillAutori(citTemp.get(j)));
				citTemp.set(j, this.fillCategorie(citTemp.get(j)));
				fillMatrixWithRiferimenti(data, fillTemp, citTemp.get(j));
				data[fillTemp][7] = listaRiferimenti.get(i).getTitolo();
				fillTemp = fillTemp + 1;
			}
		}
		return data;
	}
}
