package daopkg;

import datalpkg.Riferimento;
import datalpkg.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class UtenteDAOPostgres implements UtenteDAO {
    private final PostgresDBConnection dbc;
    private static UtenteDAOPostgres instance;
    UtenteDAOPostgres() {
        dbc = PostgresDBConnection.getInstance();
    }
    public static UtenteDAOPostgres getInstance() {
        if (instance == null)
            instance = new UtenteDAOPostgres();
        return instance;
    }
    @Override
    public List<Utente> getAll() throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT * FROM utente");
        final List<Utente> list = new ArrayList<>();
        while (rs.next()) {
            list.add(extract(rs));
        }
        return list;
    }
    @Override
    public Utente get(final int id) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT * FROM utente WHERE id_utente = " + id);
        if (rs.next())
            return extract(rs);
        else
            return null;
    }
    @Override
    public List<Utente> getByRif(final Riferimento r) throws SQLException {
        final ResultSet rs = dbc.executeQuery("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE id_riferimento = " + r.getId_Rif());
        final List<Utente> list = new ArrayList<>();
        while (rs.next()) {
            list.add(extract(rs));
        }
        return list;
    }
    @Override
    public int getNextId() throws SQLException{
        final ResultSet rs = dbc.executeQuery("SELECT MAX(id_utente) FROM utente");
        rs.next();
        return rs.getInt(1) + 1;
    }
    @Override
    public void update(final Utente obj) throws SQLException {
        String sql = "UPDATE utente SET nome_utente = '" + obj.getNome() + "',cognome_utente = '" + obj.getCognome()
                + "',data_inizio_validita = '" + obj.getInizio().toString() + "',";
        if (Objects.isNull(obj.getFine()))
            sql = sql + "NULL";
        else
            sql = sql + "'" + obj.getFine().toString() + "' ";
        sql = sql + "WHERE id_utente = " + obj.getUser_ID();
        dbc.execute(sql);
    }
    @Override
    public void insert(final Utente obj) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO utente VALUES(" + obj.getUser_ID() + ",'" + obj.getNome() + "','" + obj.getCognome()
                + "','" + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(obj.getInizio()) + "',";
        if (Objects.isNull(obj.getFine()))
            sql = sql + "NULL";
        else
            sql = sql + "'" + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(obj.getFine()) + "' ";
        sql = sql + ")";
        dbc.execute(sql);
    }

    private static Utente extract(final ResultSet rs) throws SQLException {

        return new Utente(rs.getString("nome_utente"), rs.getString("cognome_utente"), rs.getInt("id_utente"),
                rs.getDate("data_inizio_validita"), rs.getDate("data_fine_validita"));
    }

    public void closeResource()
    {
        dbc.closeConnection();
    }
}
