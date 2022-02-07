package ctrlpkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import datalpkg.Riferimento;

public class RiferimentoDAO implements DAO<Riferimento> {
	private DBConnection dbc = DBConnection.getInstance();

	@Override
	public List<Riferimento> getAll(String sql) throws SQLException 
	{
		ResultSet rs = dbc.executeQuery(sql);
		List<Riferimento> list = new ArrayList<>();
		while(rs.next())
		{
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Riferimento get(String sql) throws SQLException 
	{
		ResultSet rs = dbc.executeQuery(sql);
		if(rs.next())
			return extract(rs);
		else
			return null;
	}

	@Override
	public void update(Riferimento obj) throws SQLException 
	{
		String sql = "UPDATE riferimenti_biblio SET titolo_riferimento = '"+obj.getTitolo()+"', data_riferimento = '"+obj.getDataCreazione().toString()+"',on_line = '"+obj.getDigitale().toString()+"',tipo = '"+obj.getTipo()
		+"',url = ";
		if(Objects.isNull(obj.getURL()))
			sql = sql.concat("NULL, doi = ");
		else
			sql = sql.concat("'"+obj.getURL()+"', doi = ");
		if(Objects.isNull(obj.getDOI()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat(""+obj.getDOI()+",");
		sql = sql.concat("descr_riferimento = '"+obj.getDescrizione()+"',descr_autore = '"+obj.getDescr_autore()+"' ");
		sql = sql.concat("WHERE id_riferimento = "+obj.getId_Rif());
		dbc.execute(sql);
	}

	@Override
	public void delete(Riferimento obj) throws SQLException
	{
		String sql = "DELETE FROM riferimenti_biblio WHERE id_riferimento = "+obj.getId_Rif();
		dbc.execute(sql);
	}

	@Override
	public void insert(Riferimento obj) throws SQLException 
	{
		String sql = "INSERT INTO riferimenti_biblio VALUES("+obj.getId_Rif()+",'"
				+obj.getTitolo()+"','"+obj.getDataCreazione().toString()+"','"+obj.getDigitale().toString()+"','"+obj.getTipo()
				+"',";
		if(Objects.isNull(obj.getURL()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat("'"+obj.getURL()+"',");
		if(Objects.isNull(obj.getDOI()))
			sql = sql.concat("NULL,");
		else
			sql = sql.concat(""+obj.getDOI()+",");
		sql = sql.concat("'"+obj.getDescrizione()+"','"+obj.getDescr_autore()+"')");
		dbc.execute(sql);
	}


	private Riferimento extract(ResultSet rs) throws SQLException {
		return new Riferimento(rs.getInt("id_riferimento"),rs.getString("titolo_riferimento"), rs.getDate("data_riferimento"), rs.getString("tipo"), rs.getString("url"),rs.getInt("doi"), rs.getBoolean("on_line"),
				rs.getString("descr_riferimento"),rs.getString("descr_autore"));
	}




}
