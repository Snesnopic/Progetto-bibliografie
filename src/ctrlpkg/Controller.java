package ctrlpkg;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import datalpkg.Categoria;
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
	public Controller() throws IOException
	{
		lf = new LoginFrame(this);
		lf.setVisible(true);
	}
	public boolean login(String CF) throws IOException
	{
		try 
		{
			dbc = DBConnection.getInstance();
			dbc.getConnection("jdbc:postgresql://localhost/bibliografie","postgres","admin"); 
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
		lf.emptyFields();
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
	public Riferimento fillCategorie(Riferimento r)
	{
		try
		{
			cDAO = new CategoriaDAO();
			r.setCategorie(cDAO.getAll("SELECT categoria.* FROM categoria NATURAL JOIN categoria_riferimento WHERE titolo='"+r.getTitolo()+"'"));
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
		return r;
	}
	public Riferimento fillAutori(Riferimento r)
	{
		try 
		{
			uDAO = new UtenteDAO();
			r.setAutori(uDAO.getAll("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE titolo = '"+r.getTitolo()+"'"));
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
		return r;
	}

	public List<Riferimento> retrieveCitazioni(Riferimento r)
	{
		try 
		{
			rDAO = new RiferimentoDAO();
			return rDAO.getAll("SELECT riferimento.* FROM riferimento JOIN citazione ON titolo = titolo_citante AND titolo_citato = '"+r.getTitolo()+"'");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public List<Categoria> findSottocategorie(String nomeCategoria)
	{
		
		try
		{
			cDAO = new CategoriaDAO();
			List<Categoria> listaTemp = cDAO.getAll("SELECT ");
			//TODO: query (o funzione) per avere tutte le sottocategorie di una data categoria
			
			
			return listaTemp;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}

	public Object[][] RicercaToObjectMatrix(String testo, String categoria, boolean[] tipi,String filtro)
	{
		
		try 
		{
			rDAO = new RiferimentoDAO();
			String query = "SELECT DISTINCT riferimento.* FROM riferimento, categoria_riferimento ";

			switch(filtro)
			{
				case "Titolo":
					query = query.concat("WHERE riferimento.titolo LIKE '%"+testo+"%' ");
					break;
				case "Autore":
					query = query.concat(", autore_riferimento WHERE autore_riferimento.titolo = riferimento.titolo AND cf = '"+testo+"' ");
					break;
				case "DOI":
					query = query.concat("WHERE digitale = false AND doi_url = '"+testo+"' ");
					break;
			}

			if(!Objects.equals(categoria, "Qualsiasi"))
				query = query.concat("AND categoria_riferimento.titolo = riferimento.titolo AND categoria_riferimento.nome = '"+categoria+"'");
			System.out.println(query);
			
			
			//TODO: query dinamica per la ricerca delle categorie
			List<Riferimento> risultati = rDAO.getAll(query);
			return RiferimentiToObjectMatrix(risultati,risultati.size());
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
		
	}
	public Object[][] RiferimentiToObjectMatrix(List<Riferimento> listaRiferimenti, int righe)
	{
		Object[][] data = new Object[righe][6];
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
			data[i][5] = listaRiferimenti.get(i).getTipo();
		}
		return data;
	}
	public Object[][] CitazioniToObjectMatrix(List<Riferimento> listaRiferimenti, int righe)
	{
		Object[][] data = new Object[righe][7];
		int fillTemp = 0;
		rDAO = new RiferimentoDAO();
		for(int i=0;i<listaRiferimenti.size()&&fillTemp<righe;i++)
		{
			List<Riferimento> citTemp = retrieveCitazioni(listaRiferimenti.get(i));
			for(int j=0;j<citTemp.size()&&fillTemp<righe;j++)
			{
				citTemp.set(j, this.fillAutori(citTemp.get(j)));
				citTemp.set(j, this.fillCategorie(citTemp.get(j)));
				data[fillTemp][0] = citTemp.get(j).getTitolo();
				data[fillTemp][1] = citTemp.get(j).autoriToString();
				data[fillTemp][2] = citTemp.get(j).getDataCreazione();
				data[fillTemp][3] = citTemp.get(j).getDOI_URL();
				data[fillTemp][4] = citTemp.get(j).categorieToString();
				data[fillTemp][5] = citTemp.get(j).getTipo();
				data[fillTemp][6] = listaRiferimenti.get(i).getTitolo();
				fillTemp = fillTemp + 1;
			}
		}
		return data;
	}
}
