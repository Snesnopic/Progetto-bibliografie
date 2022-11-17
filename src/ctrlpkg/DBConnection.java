package ctrlpkg;

import java.sql.*;

public class DBConnection {
	private static DBConnection instance;
	private Connection cn;
	private Statement st;

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public void getConnection() throws SQLException {
		cn = DriverManager.getConnection("jdbc:postgresql://localhost/Gestione_Riferimenti_Bibliografici", "postgres", "admin");
	}

	public void closeConnection() throws SQLException {
		cn.close();
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
