package ctrlpkg;
import java.sql.SQLException;
import java.util.ArrayList;

import datalpkg.Riferimento;
import javax.swing.JOptionPane;


import guipkg.LoginFrame;
import guipkg.MainFrame;

public class Controller {
	
	DBConnection dbc;
	LoginFrame lf;
	MainFrame mf;
	public static void main(String[] args)
	{
		
		try {
		new Controller();
		JOptionPane.showMessageDialog(null,"Errore: romi uallera storta");
		}
		catch(Throwable a)
		{
			JOptionPane.showMessageDialog(null,"Errore: "+a.getMessage());
		}
	}
	public Controller()
	{
		lf = new LoginFrame(this);
		mf = new MainFrame(this);
		lf.setVisible(true);
	}
	public boolean login(String CF)
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
	public void retrieveNome(String user_id)
	{
		
	}
	public void retrieveCognome(String user_id)
	{
		
	}
	public ArrayList<Riferimento> retrieveRiferimenti(String user_id)
	{
		return null;
		
	}
	public ArrayList<Riferimento> retrieveCitazioni(String user_id)
	{
		return null;
		
	}
	
}
