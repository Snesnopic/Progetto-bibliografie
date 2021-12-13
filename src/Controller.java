import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
	Connection db;
	LoginFrame lf;
	MainFrame mf;
	public static void main(String[] args) throws IOException 
	{
		new Controller();
	}
	public Controller() throws IOException
	{
		lf = new LoginFrame(this);
		mf = new MainFrame(this);
		lf.setVisible(true);
	}
	public boolean login(String username,char[] password)
	{
		try {
			db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
			lf.setVisible(false);
			mf.setVisible(true);
			return true;
		} catch (SQLException e) {
			// ERRORE DATABASE, NON LOGIN
			e.printStackTrace();
		}
		//vero login
		
		return true;
	}
	public void logout() throws SQLException
	{
		db.close();
		mf.setVisible(false);
		lf.setVisible(true);
	}
}
