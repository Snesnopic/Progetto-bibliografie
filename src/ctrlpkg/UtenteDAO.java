package ctrlpkg;

import datalpkg.Riferimento;
import datalpkg.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UtenteDAO implements DAO<Utente> {
	private final DBConnection dbc = DBConnection.getInstance();
	private static UtenteDAO instance = null;
	private UtenteDAO() {

	}
	public static UtenteDAO getInstance() {
		if (instance == null)
			instance = new UtenteDAO();
		return instance;
	}

	@Override
	public List<Utente> getAll() throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM utente");
		List<Utente> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Utente get(int id) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM utente WHERE id_utente = " + id);
		if (rs.next())
			return extract(rs);
		else
			return null;
	}

	public List<Utente> getByRif(Riferimento r) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE id_riferimento = " + r.getId_Rif());
		List<Utente> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	public int getNextId() throws SQLException
	{
		ResultSet rs = dbc.executeQuery("SELECT MAX(id_utente) FROM utente");
		rs.next();
		return rs.getInt(1) + 1;
	}

	@Override
	public void update(Utente obj) throws SQLException {
		String sql = "UPDATE utente SET nome_utente = '" + obj.getNome() + "',cognome_utente = '" + obj.getCognome()
				+ "',data_inizio_validita = '" + obj.getInizio().toString() + "',";
		if (Objects.isNull(obj.getFine()))
			sql = sql.concat("NULL");
		else
			sql = sql.concat("'" + obj.getFine().toString() + "' ");
		sql = sql.concat("WHERE id_utente = " + obj.getUser_ID());
		dbc.execute(sql);
	}

	@Override
	public void insert(Utente obj) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO utente VALUES(" + obj.getUser_ID() + ",'" + obj.getNome() + "','" + obj.getCognome()
				+ "','" + new SimpleDateFormat("dd/MM/yyyy").format(obj.getInizio()) + "',";
		if (Objects.isNull(obj.getFine()))
			sql = sql.concat("NULL");
		else
			sql = sql.concat("'" + new SimpleDateFormat("dd/MM/yyyy").format(obj.getFine()) + "' ");
		sql = sql.concat(")");
		System.out.println(sql);
		dbc.execute(sql);
	}

	private Utente extract(ResultSet rs) throws SQLException {

		return new Utente(rs.getString("nome_utente"), rs.getString("cognome_utente"), rs.getInt("id_utente"),
				rs.getDate("data_inizio_validita"), rs.getDate("data_fine_validita"));
	}
}
