package ctrlpkg;
import java.sql.SQLException;
import guipkg.*;
import javax.swing.JOptionPane;

import guipkg.LoginFrame;
import guipkg.MainFrame;

public class Controller {
	
	DBConnection dbc;
	LoginFrame lf;
	MainFrame mf;
	public static void main(String[] args)
	{
		new Controller();
	}
	public Controller()
	{
		lf = new LoginFrame(this);
		mf = new MainFrame(this);
		lf.setVisible(true);
	}
	public boolean login(String username,char[] password)
	{
		try {
			dbc = DBConnection.getInstance();
			dbc.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin"); 
			lf.setVisible(false);
			mf.setVisible(true);
			return true;
		} catch (SQLException e) {
			// ERRORE DATABASE, NON LOGIN
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
		
		return false;
	}
	public void logout()
	{
		try {
			dbc.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
		mf.setVisible(false);
		lf.setVisible(true);
	}
}
