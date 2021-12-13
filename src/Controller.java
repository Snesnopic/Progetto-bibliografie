import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
	Connection db;
	public void login(String username,char[] password)
	{
		try {
			db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lpmedia", "postgres", "admin");
		} catch (SQLException e) {
			// ERRORE DATABASE, NON LOGIN
			e.printStackTrace();
		}
		//vero login
	}
	public void logout() throws SQLException
	{
		db.close();
	}
}
