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
import java.sql.ResultSet;
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
			ResultSet rs = dbc.executeQuery("SELECT MAX(id_utente) FROM utente");
			rs.next();
			rf = new RegisterFrame(this, rs.getInt(1) + 1);
			lf.setVisible(false);
			rf.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	public void updateRiferimento(int id_rif, String titolo, String descr, String URL, Integer doi, Boolean isDigital, Date data, String tipo) {
		try {
			Riferimento r = new Riferimento(id_rif, titolo, data, tipo, URL, doi, isDigital, descr);
			RiferimentoDAO rDAO = new RiferimentoDAO();
			rDAO.update(r);
			JOptionPane.showMessageDialog(null, "Riferimento aggiornato correttamente!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	public void creaUtente(int user_ID, String nome, String cognome) {
		try {
			Utente u = new Utente(nome, cognome, user_ID, new Date(System.currentTimeMillis()), null);
			UtenteDAO uDAO = new UtenteDAO();
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
			dbc.execute(
					"DELETE FROM riferimenti_biblio WHERE id_riferimento IN (SELECT r. id_riferimento FROM riferimenti_biblio r JOIN autore_riferimento a ON r.id_riferimento = a.id_riferimento WHERE a.id_utente = "
							+ user_ID + "AND r.titolo_riferimento = '" + nomeRif + "')");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public String retrieveCodiciSottocategorie(String nomeCat) {
		ResultSet rs;
		try {
			rs = dbc.executeQuery("SELECT sub_cat(" + retrieveCodiceCategoria(nomeCat) + ")");
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public int retrieveCodiceCategoria(String nomeCat) throws SQLException {
		ResultSet rs;
		rs = dbc.executeQuery("SELECT id_categoria FROM categoria WHERE descr_categoria = '" + nomeCat + "'");
		rs.next();
		return rs.getInt(1);
	}

	public void creaRiferimento(String nomeRif, Date data, String tipo, String URL, Integer DOI, boolean dig,
			String descr_rif, ArrayList<Integer> autori, ArrayList<Integer> categorie,
			ArrayList<Integer> citazioni) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT MAX(Id_riferimento) FROM riferimenti_biblio");
			rs.next();
			int id_Rif = rs.getInt(1) + 1;
			if (!autori.contains(retrieveID()))
				autori.add(0, retrieveID());
			Riferimento r = new Riferimento(id_Rif, nomeRif, data, tipo, URL, DOI, dig, descr_rif);
			RiferimentoDAO rDAO = new RiferimentoDAO();
			rDAO.insert(r);
			for (int i = 0; i < autori.size(); i++) {
				dbc.execute("INSERT INTO autore_riferimento VALUES (" + autori.get(i) + ",null," + id_Rif + ")");
			}
			for (int i = 0; i < categorie.size(); i++) {
				dbc.execute("INSERT INTO associativa_riferimenti_categoria VALUES (" + id_Rif + "," + categorie.get(i)
						+ ")");
			}
			for (int i = 0; i < citazioni.size(); i++) {
				dbc.execute("INSERT INTO associazione_riferimenti VALUES(" + id_Rif + "," + citazioni.get(i) + ")");
			}
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
			uDAO = new UtenteDAO();
			loginUser = uDAO.get("SELECT * FROM utente WHERE id_utente ='" + user_ID + "'");
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
			rDAO = new RiferimentoDAO();
			return rDAO.getAll(
					"SELECT riferimenti_biblio.* FROM riferimenti_biblio NATURAL JOIN autore_riferimento WHERE id_utente ='"
							+ ID + "'");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Riferimento fillCategorie(Riferimento r) {
		try {
			cDAO = new CategoriaDAO();
			r.setCategorie(cDAO.getAll(
					"SELECT categoria.* FROM categoria NATURAL JOIN associativa_riferimenti_categoria WHERE id_riferimento='"
							+ r.getId_Rif() + "'"));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
		return r;
	}

	public Riferimento fillAutori(Riferimento r) {
		try {
			uDAO = new UtenteDAO();
			r.setAutori(
					uDAO.getAll("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE id_riferimento = '"
							+ r.getId_Rif() + "'"));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
		return r;
	}

	public List<Riferimento> retrieveCitazioni(Riferimento r) {
		try {
			rDAO = new RiferimentoDAO();
			return rDAO.getAll(
					"SELECT riferimenti_biblio.* FROM riferimenti_biblio JOIN associazione_riferimenti ON riferimenti_biblio.id_riferimento = associazione"
							+ "_riferimenti.id_riferimento AND id_riferimento_associato = '" + r.getId_Rif() + "'");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Object[][] RicercaToObjectMatrix(String testo, String categoria, boolean[] tipi, String filtro) {

		try {
			rDAO = new RiferimentoDAO();
			String query = "SELECT DISTINCT riferimenti_biblio.* FROM riferimenti_biblio, associativa_riferimenti_categoria ";
			// condizioni per filtro
			switch (filtro) {
			case "Titolo":
				query = query.concat("WHERE titolo_riferimento LIKE '%" + testo + "%' ");
				break;
			case "Autore":
				query = query.concat(
						", autore_riferimento,utente WHERE autore_riferimento.id_riferimento = riferimenti_biblio.id_riferimento AND autore_riferimento.id_utente = utente.id_utente AND (nome_utente LIKE '%"
								+ testo + "%' OR cognome_utente LIKE '%" + testo + "%') ");
				break;
			case "DOI":
				query = query.concat("WHERE doi = '" + testo + "' ");
				break;
			}
			// condizioni per categorie
			if (!Objects.equals(categoria, "Qualsiasi")) {
				query = query.concat(
						"AND associativa_riferimenti_categoria.id_riferimento = riferimenti_biblio.id_riferimento AND associativa_riferimenti_categoria.id_categoria IN ("
								+ retrieveCodiceCategoria(categoria));
				String sottoCat = retrieveCodiciSottocategorie(categoria);
				if (!sottoCat.isEmpty())
					query = query.concat("," + sottoCat);
				query = query.concat(") ");
			}
			// condizioni per tipi
			query = query.concat("AND tipo IN ('");
			boolean multipleTypes = false;
			if (tipi[0]) {
				query = query.concat("Rivista'");
				multipleTypes = true;
			}
			if (tipi[1]) {
				if (multipleTypes)
					query = query.concat(",'");
				query = query.concat("Libro'");
				multipleTypes = true;
			}
			if (tipi[2]) {
				if (multipleTypes)
					query = query.concat(",'");
				query = query.concat("Fascicolo'");
				multipleTypes = true;
			}
			if (tipi[3]) {
				if (multipleTypes)
					query = query.concat(",'");
				query = query.concat("Articolo'");
			}
			if (tipi[4]) {
				if (multipleTypes)
					query = query.concat(",'");
				query = query.concat("Conferenza'");
			}
			query = query.concat(")");

			List<Riferimento> risultati = rDAO.getAll(query);
			return RiferimentiToObjectMatrix(risultati, risultati.size());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public void creaCategoria(String nomeCat, String nomeSuperCat, int user_ID) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT MAX(id_categoria) FROM categoria");
			rs.next();
			Categoria c;
			if (Objects.isNull(nomeSuperCat))
				c = new Categoria(rs.getInt(1) + 1, nomeCat, user_ID);
			else {
				c = new Categoria(rs.getInt(1) + 1, nomeCat, user_ID, retrieveCodiceCategoria(nomeSuperCat));
			}

			CategoriaDAO cDAO = new CategoriaDAO();
			cDAO.insert(c);
			JOptionPane.showMessageDialog(null, "Categoria creata!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public String[] getCitazioni() {
		try {
			ArrayList<String> arrCitazioni = new ArrayList<>();
			ResultSet rs = dbc.executeQuery("SELECT * FROM riferimenti_biblio ORDER BY id_riferimento ASC");
			while (rs.next()) {
				arrCitazioni.add(rs.getString("titolo_riferimento"));
			}
			return arrCitazioni.toArray(new String[arrCitazioni.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String[] getRiferimenti() {
		try {
			ArrayList<String> arrRiferimenti = new ArrayList<>();

			ResultSet rs = dbc.executeQuery(
					"SELECT * FROM riferimenti_biblio JOIN autore_riferimento ON autore_riferimento.id_riferimento = riferimenti_biblio.id_riferimento WHERE id_utente = "
							+ this.retrieveID());
			while (rs.next()) {
				arrRiferimenti.add(rs.getString("titolo_riferimento"));
			}
			return arrRiferimenti.toArray(new String[arrRiferimenti.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String[] getUtenti() {
		try {
			ArrayList<String> arrUtenti = new ArrayList<>();

			ResultSet rs = dbc.executeQuery("SELECT * FROM utente ORDER BY id_utente ASC");
			while (rs.next()) {
				arrUtenti.add(rs.getString("nome_utente") + " " + rs.getString("cognome_utente"));
			}
			return arrUtenti.toArray(new String[arrUtenti.size()]);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Integer RetrieveCodiceRiferimento(String nome) {
		try {
			ResultSet rs = dbc.executeQuery(
					"SELECT id_riferimento FROM riferimenti_biblio WHERE titolo_riferimento = '" + nome + "'");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Integer getDOI(int id_rif) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT doi FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Boolean getOnline(int id_rif) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT on_line FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public Date getData(int id_rif) {
		try {
			ResultSet rs = dbc
					.executeQuery("SELECT data_riferimento FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getDate(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}

	}

	public String getTipo(int id_rif) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT tipo FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}
	
	public String getISNN(int id_rif)
	{
		try {
			ResultSet rs = dbc.executeQuery("SELECT ISNN FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String getISBN(int id_rif)
	{
		try {
			ResultSet rs = dbc.executeQuery("SELECT ISNN FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}
	
	public String getURL(int id_rif) {
		try {
			ResultSet rs = dbc.executeQuery("SELECT url FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}

	public String getDescrizione(int id_rif) {
		try {
			ResultSet rs = dbc
					.executeQuery("SELECT descr_riferimento FROM riferimenti_biblio WHERE id_riferimento = " + id_rif);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
			return null;
		}
	}


	public String[] getCategorie(boolean b) {
		try {
			ArrayList<String> arrCategorie = new ArrayList<>();
			if (b)
				arrCategorie.add("Qualsiasi");
			ResultSet rs = dbc.executeQuery("SELECT descr_categoria FROM categoria ORDER BY id_categoria ASC");
			while (rs.next()) {
				arrCategorie.add(rs.getString(1));
			}
			return arrCategorie.toArray(new String[arrCategorie.size()]);
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
		rDAO = new RiferimentoDAO();
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
