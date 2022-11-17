package ctrlpkg;

import datalpkg.Categoria;
import datalpkg.Riferimento;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria> {
	private final DBConnection dbc = DBConnection.getInstance();
	private static CategoriaDAO instance = null;
	private CategoriaDAO(){

	}
	public static CategoriaDAO getInstance() {
		if (instance == null)
			instance = new CategoriaDAO();
		return instance;
	}

	@Override
	public List<Categoria> getAll() throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM categoria ORDER BY id_categoria ASC");
		List<Categoria> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Categoria get(int id) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT * FROM categoria WHERE id_categoria = " + id);
		if (rs.next())
			return extract(rs);
		else
			return null;
	}
	public List<Categoria> getByRif(Riferimento r) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT categoria.* FROM categoria NATURAL JOIN associativa_riferimenti_categoria WHERE id_riferimento = "+ r.getId_Rif() );
		List<Categoria> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	public String getSubCatCodes(String nomeCat) throws SQLException {
		ResultSet rs;
		rs = dbc.executeQuery("SELECT sub_cat(" + getByName(nomeCat) + ")");
		rs.next();
		return rs.getString(1);
	}

	public Categoria getByName(String nomeCat) throws SQLException {
		ResultSet rs = dbc.executeQuery("SELECT id_categoria FROM categoria WHERE descr_categoria = '" + nomeCat + "'");
		if (rs.next())
			return extract(rs);
		else
			return null;
	}
	public int getNextId() throws SQLException
	{
		ResultSet rs = dbc.executeQuery("SELECT MAX(id_categoria) FROM categoria");
		rs.next();
		return rs.getInt(1) + 1;
	}
	@Override
	public void update(Categoria obj) throws SQLException {
		String sql = "UPDATE categoria SET descr_categoria = '" + obj.getNome() + "',id_super_categoria = "
				+ obj.getGeneraliz() + ",id_utente = " + obj.getAutore() + " WHERE id_categoria = " + obj.getId_Cat();
		dbc.execute(sql);
	}

	@Override
	public void insert(Categoria obj) throws SQLException {
		String sql = "INSERT INTO categoria VALUES(" + obj.getId_Cat() + ",'" + obj.getNome() + "'";
		if (obj.getGeneraliz() == 0)
			sql = sql.concat(",null");
		else {
			sql = sql.concat("," + obj.getGeneraliz());
		}
		sql = sql.concat("," + obj.getAutore() + ")");
		dbc.execute(sql);
	}

	private Categoria extract(ResultSet rs) throws SQLException {
		return new Categoria(rs.getInt("id_categoria"), rs.getString("descr_categoria"), rs.getInt("id_utente"),
				rs.getInt("id_super_categoria"));
	}
}
