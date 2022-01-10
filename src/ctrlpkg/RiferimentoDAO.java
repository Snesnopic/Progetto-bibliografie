package ctrlpkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import datalpkg.Riferimento;

public class RiferimentoDAO implements DAO<Riferimento> {
	private DBConnection dbc = DBConnection.getInstance();
	
	@Override
	public List<Riferimento> getAll(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		List<Riferimento> list = new ArrayList<Riferimento>();
		while(rs.next())
		{
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Riferimento get(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		if(rs.next())
			return extract(rs);
		else
			return null;
	}

	@Override
	public void update(Riferimento obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Riferimento obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Riferimento obj, String sql) {
		// TODO Auto-generated method stub
		
	}
	

	private Riferimento extract(ResultSet rs) throws SQLException {
		Riferimento r = new Riferimento(rs.getString("titolo"), rs.getDate("data_creazione"), rs.getString("tipo"), rs.getString("doi_url"), rs.getBoolean("digitale"));
		return r;
	}
	

	

}
