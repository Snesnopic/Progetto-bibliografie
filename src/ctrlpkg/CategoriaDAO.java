package ctrlpkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import datalpkg.Categoria;

public class CategoriaDAO implements DAO<Categoria> {
	private DBConnection dbc = DBConnection.getInstance();

	@Override
	public List<Categoria> getAll(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		List<Categoria> list = new ArrayList<>();
		while(rs.next())
		{
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Categoria get(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		if(rs.next())
			return extract(rs);
		else
			return null;
	}

	@Override
	public void update(Categoria obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categoria obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Categoria obj, String sql) {
		// TODO Auto-generated method stub
		
	}

	private Categoria extract(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Categoria c = new Categoria(rs.getString("nome"), rs.getString("descrizione"), rs.getString("cf"));
		String generalizzazione = rs.getString("nome_generaliz");
		if(generalizzazione!=null)
			c.setGeneraliz(this.get("SELECT * FROM categoria WHERE nome='"+rs.getString("nome_generaliz")+"'"));
		return c;
	}

	
	

}
