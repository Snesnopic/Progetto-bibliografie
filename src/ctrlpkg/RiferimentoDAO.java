package ctrlpkg;

import datalpkg.Riferimento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RiferimentoDAO implements DAO<Riferimento> {
	private final DBConnection dbc = DBConnection.getInstance();
	private static RiferimentoDAO instance = null;
	private RiferimentoDAO() {

	}
	public static RiferimentoDAO getInstance() {
		if (instance == null)
			instance = new RiferimentoDAO();
		return instance;
	}
	@Override
	public List<Riferimento> getAll() throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM riferimenti_biblio ORDER BY id_riferimento ASC");
		List<Riferimento> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Riferimento get(int id) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM riferimenti_biblio WHERE id_riferimento " + id);
		if (rs.next())
			return extract(rs);
		else
			return null;
	}

	public List<Riferimento> getByUser(int id) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT riferimenti_biblio.* FROM riferimenti_biblio NATURAL JOIN autore_riferimento WHERE id_utente ='"
				+ id + "'");
		List<Riferimento> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	public Riferimento getByName(String name) throws SQLException{
		ResultSet rs = dbc.executeQuery("SELECT id_riferimento FROM riferimenti_biblio WHERE titolo_riferimento = '" + name + "'");
		if (rs.next())
			return extract(rs);
		else
			return null;
	}

	public List<Riferimento> getByRif(Riferimento r) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT riferimenti_biblio.* FROM riferimenti_biblio JOIN associazione_riferimenti ON riferimenti_biblio.id_riferimento = associazione"
				+ "_riferimenti.id_riferimento AND id_riferimento_associato = '" + r.getId_Rif() + "'");
		List<Riferimento> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	public List<Riferimento> getBySearch(String testo, String categoria, boolean[] tipi, String filtro) throws SQLException {
		CategoriaDAO cDAO = CategoriaDAO.getInstance();
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
							+ cDAO.getByName(categoria));
			String sottoCat = cDAO.getSubCatCodes(categoria);
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
		ResultSet rs = dbc.executeQuery(query);
		List<Riferimento> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	public int getNextId() throws SQLException
	{
		ResultSet rs = dbc.executeQuery("SELECT MAX(Id_riferimento) FROM riferimenti_biblio");
		rs.next();
		return rs.getInt(1) + 1;
	}

	@Override
	public void update(Riferimento obj) throws SQLException {
		String sql = "UPDATE riferimenti_biblio SET titolo_riferimento = '" + obj.getTitolo()
				+ "', data_riferimento = '" + obj.getDataCreazione().toString() + "',on_line = '"
				+ obj.getDigitale().toString() + "',Tipo = '" + obj.getTipo() + "',url = ";
		if (Objects.isNull(obj.getURL()))
			sql = sql.concat("NULL, doi = ");
		else
			sql = sql.concat("'" + obj.getURL() + "', doi = ");
		if (Objects.isNull(obj.getDOI()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat("" + obj.getDOI() + ",");
		sql = sql.concat(
				"descr_riferimento = '" + obj.getDescrizione() + "' ");
		sql = sql.concat("WHERE id_riferimento = " + obj.getId_Rif());
		dbc.execute(sql);
	}

	@Override
	public void insert(Riferimento obj) throws SQLException {
		String sql = "INSERT INTO riferimenti_biblio VALUES(" + obj.getId_Rif() + ",'" + obj.getTitolo() + "','"
				+ obj.getDataCreazione().toString() + "','" + obj.getDigitale().toString() + "','" + obj.getTipo()
				+ "',";
		if (Objects.isNull(obj.getURL()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat("'" + obj.getURL() + "',");
		if (Objects.isNull(obj.getDOI()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat("" + obj.getDOI() + ",");
		sql = sql.concat("'" + obj.getDescrizione() + "')");
		dbc.execute(sql);
	}
	public void insert(Riferimento obj,ArrayList<Integer> autori, ArrayList<Integer> categorie,
					   ArrayList<Integer> citazioni) throws SQLException {
		insert(obj);
		for (Integer integer : autori) {
			dbc.execute("INSERT INTO autore_riferimento VALUES (" + integer + ",null," + obj.getId_Rif() + ")");
		}
		for (Integer integer : categorie) {
			dbc.execute("INSERT INTO associativa_riferimenti_categoria VALUES (" + obj.getId_Rif() + "," + integer
					+ ")");
		}
		for (Integer integer : citazioni) {
			dbc.execute("INSERT INTO associazione_riferimenti VALUES(" + obj.getId_Rif() + "," + integer + ")");
		}
	}

	private Riferimento extract(ResultSet rs) throws SQLException {
		return new Riferimento(rs.getInt("id_riferimento"), rs.getString("titolo_riferimento"),
				rs.getDate("data_riferimento"), rs.getString("tipo"), rs.getString("url"), rs.getInt("doi"),
				rs.getBoolean("on_line"), rs.getString("descr_riferimento"));
	}

	public void delete(Riferimento r, int user_ID) throws SQLException {
		dbc.execute(
				"DELETE FROM riferimenti_biblio WHERE id_riferimento IN (SELECT r. id_riferimento FROM riferimenti_biblio r JOIN autore_riferimento a ON r.id_riferimento = a.id_riferimento WHERE a.id_utente = "
						+ user_ID + "AND r.titolo_riferimento = '" + r.getTitolo() + "')");
	}


}
