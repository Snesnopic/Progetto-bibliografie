package daopkg;

import javax.swing.*;
import java.sql.*;

public class PostgresDBConnection {
	private static PostgresDBConnection instance;
	private Connection cn;
	private Statement st;

	private PostgresDBConnection() {
		getConnection();
	}

	public static PostgresDBConnection getInstance() {
		if (instance == null)
			instance = new PostgresDBConnection();
		return instance;
	}

	public void getConnection(){
		try
		{
		cn = DriverManager.getConnection("jdbc:postgresql://localhost/Gestione_Riferimenti_Bibliografici", "postgres", "admin");
		}
		catch (final SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public void closeConnection(){
		try
		{
			cn.close();
		}
		catch (final SQLException e) {
			JOptionPane.showMessageDialog(null,
					"DB Error:\n" + e.getMessage() + "\nCodice errore: " + e.getErrorCode());
		}
	}

	public ResultSet executeQuery(final String query) throws SQLException {
		st = cn.createStatement();
		return st.executeQuery(query);
	}

	public void execute(final String query) throws SQLException {
		st = cn.createStatement();
		st.execute(query);
	}
}
