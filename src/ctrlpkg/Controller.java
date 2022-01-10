package ctrlpkg;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import datalpkg.Riferimento;
import datalpkg.Utente;
import guipkg.LoginFrame;
import guipkg.MainFrame;

public class Controller {
	UtenteDAO uDAO;
	RiferimentoDAO rDAO;
	CategoriaDAO cDAO;
	Utente loginUser;
	DBConnection dbc;
	LoginFrame lf;
	MainFrame mf;
	public static void main(String[] args)
	{
		
		try 
		{
			new Controller();
		}
		catch(Throwable a)
		{
			JOptionPane.showMessageDialog(null,"Errore: "+a.getMessage());
		}
	}
	public Controller()
	{
		lf = new LoginFrame(this);
		lf.setVisible(true);
	}
	public boolean login(String CF)
	{
		try 
		{
			dbc = DBConnection.getInstance();
			dbc.getConnection("jdbc:postgresql://localhost:5432/bibliografie","postgres","admin"); 
			
			uDAO = new UtenteDAO();
			loginUser = uDAO.get("SELECT * FROM utente WHERE cf ='"+CF+"'");
			if(Objects.isNull(loginUser))
				return false;
			else
			{
				mf = new MainFrame(this);
				lf.setVisible(false);
				mf.setVisible(true);
				return true;
			}
				
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
		
		return false;
	}
	public void logout()
	{
		try
		{
			dbc.closeConnection();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
		mf.setVisible(false);
		lf.setVisible(true);
	}
	public String retrieveNome()
	{
		return loginUser.getNome();
	}
	public String retrieveCognome()
	{
		return loginUser.getCognome();
	}
	public String retrieveCF()
	{
		return loginUser.getCf();
	}
	
	public List<Riferimento> retrieveRiferimenti(String CF)
	{
		try 
		{
			rDAO = new RiferimentoDAO();
			return rDAO.getAll("SELECT riferimento.* FROM riferimento NATURAL JOIN autore_riferimento WHERE cf ='"+CF+"'");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public Riferimento fillCategorie(Riferimento r) throws SQLException {
		cDAO = new CategoriaDAO();
		r.setCategorie(cDAO.getAll("SELECT categoria.* FROM categoria NATURAL JOIN categoria_riferimento WHERE titolo='"+r.getTitolo()+"'"));
		return r;
	}
	public Riferimento fillAutori(Riferimento r) throws SQLException
	{
		uDAO = new UtenteDAO();
		r.setAutori(uDAO.getAll("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE titolo = '"+r.getTitolo()+"'"));
		return r;
	}
	public List<Riferimento> retrieveCitazioni(String CF)
	{
		try 
		{
			rDAO = new RiferimentoDAO();
			return rDAO.getAll("SELECT riferimento.* FROM riferimento CROSS JOIN autore_riferimento CROSS JOIN citazione WHERE cf ='"+CF+"' AND titolo_citato = autore_riferimento.titolo AND riferimento.titolo = titolo_citante");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public Object[][] listToObjectMatrix(List<Riferimento> listaRiferimenti, int righe)
	{
		Object[][] data = new Object[righe][5];
		try 
		{
			rDAO = new RiferimentoDAO();
			for(int i=0;i<listaRiferimenti.size()&&i<righe;i++)
			{
				listaRiferimenti.set(i, this.fillAutori(listaRiferimenti.get(i)));
				listaRiferimenti.set(i, this.fillCategorie(listaRiferimenti.get(i)));
			}
			for(int i=0;i<listaRiferimenti.size()&&i<righe;i++)
			{
				data[i][0] = listaRiferimenti.get(i).getTitolo();
				data[i][1] = listaRiferimenti.get(i).autoriToString();
				data[i][2] = listaRiferimenti.get(i).getDataCreazione();
				data[i][3] = listaRiferimenti.get(i).getDOI_URL();
				data[i][4] = listaRiferimenti.get(i).categorieToString();
			}
			return data;
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	
}
