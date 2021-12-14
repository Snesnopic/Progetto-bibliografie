import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static DBConnection instance = null;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private DBConnection() {}
	public static DBConnection getInstance()
	{
		if(instance == null)
			instance = new DBConnection();
		return instance;
	}
	
	public void getConnection(String url,String username,String password) throws SQLException
	{
		cn = DriverManager.getConnection(url, username, password);
	}
	public void closeConnection() throws SQLException
	{
		cn.close();
	}
}
