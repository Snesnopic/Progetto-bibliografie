package ctrlpkg;

import datalpkg.Categoria;
import datalpkg.Riferimento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOPostgres implements CategoriaDAO {
    private final PostgresDBConnection dbc;
    private static CategoriaDAOPostgres instance;
    CategoriaDAOPostgres(){
        dbc = PostgresDBConnection.getInstance();
    }
    public static CategoriaDAOPostgres getInstance() {
        if (instance == null)
            instance = new CategoriaDAOPostgres();
        return instance;
    }
    @Override
    public List<Categoria> getAll() throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT * FROM categoria ORDER BY id_categoria ASC");
        final List<Categoria> list = new ArrayList<>();
        while (rs.next()) {
            list.add(extract(rs));
        }
        return list;
    }

    @Override
    public Categoria get(final int id) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT * FROM categoria WHERE id_categoria = " + id);
        if (rs.next())
            return extract(rs);
        else
            return null;
    }
    @Override
    public List<Categoria> getByRif(final Riferimento r) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT categoria.* FROM categoria NATURAL JOIN associativa_riferimenti_categoria WHERE id_riferimento = "+ r.getId_Rif() );
        final List<Categoria> list = new ArrayList<>();
        while (rs.next()) {
            list.add(extract(rs));
        }
        return list;
    }

    public String getSubCatCodes(final String nomeCat) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT sub_cat(" + getByName(nomeCat) + ")");
        rs.next();
        return rs.getString(1);
    }

    public Categoria getByName(final String nomeCat) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT id_categoria FROM categoria WHERE descr_categoria = '" + nomeCat + "'");
        if (rs.next())
            return extract(rs);
        else
            return null;
    }
    @Override
    public int getNextId() throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT MAX(id_categoria) FROM categoria");
        rs.next();
        return rs.getInt(1) + 1;
    }
    @Override
    public void update(final Categoria obj) throws SQLException {
        dbc.execute("UPDATE categoria SET descr_categoria = '" + obj.getNome() + "',id_super_categoria = "
                + obj.getGeneraliz() + ",id_utente = " + obj.getAutore() + " WHERE id_categoria = " + obj.getId_Cat());
    }
    @Override
    public void insert(final Categoria obj) throws SQLException {
        String sql = "INSERT INTO categoria VALUES(" + obj.getId_Cat() + ",'" + obj.getNome() + "'";
        if (obj.getGeneraliz() == 0)
            sql = sql + ",null";
        else {
            sql = sql + "," + obj.getGeneraliz();
        }
        sql = sql + "," + obj.getAutore() + ")";
        dbc.execute(sql);
    }

    private static Categoria extract(final ResultSet rs) throws SQLException {
        return new Categoria(rs.getInt("id_categoria"), rs.getString("descr_categoria"), rs.getInt("id_utente"),
                rs.getInt("id_super_categoria"));
    }
}
