package ctrlpkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import datalpkg.Utente;

public class UtenteDAO implements DAO<Utente> {
	private DBConnection dbc = DBConnection.getInstance();
	
	@Override
	public List<Utente> getAll(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		List<Utente> list = new ArrayList<Utente>();
		while(rs.next())
		{
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Utente get(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		if(rs.next())
			return extract(rs);
		else
			return null;
	}

	@Override
	public void update(Utente obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Utente obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Utente obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	private Utente extract(ResultSet rs) throws SQLException {
		
		Utente u = new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("cf"));
		return u;
	}
}
