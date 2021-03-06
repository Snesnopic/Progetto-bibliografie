package ctrlpkg;

import datalpkg.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria> {
	private final DBConnection dbc = DBConnection.getInstance();

	@Override
	public List<Categoria> getAll(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		List<Categoria> list = new ArrayList<>();
		while (rs.next()) {
			list.add(extract(rs));
		}
		return list;
	}

	@Override
	public Categoria get(String sql) throws SQLException {
		ResultSet rs = dbc.executeQuery(sql);
		if (rs.next())
			return extract(rs);
		else
			return null;
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
