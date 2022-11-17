package ctrlpkg;

import javax.swing.*;
import java.sql.*;

public class DBConnection {
	private static DBConnection instance;
	private Connection cn;
	private Statement st;

	private DBConnection() {
		getConnection();
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();
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
