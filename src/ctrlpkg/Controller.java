package ctrlpkg;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import datalpkg.Categoria;
import datalpkg.Riferimento;
import datalpkg.Utente;
import guipkg.LoginFrame;
import guipkg.MainFrame;
import guipkg.RegisterFrame;

public class Controller {
	UtenteDAO uDAO;
	RiferimentoDAO rDAO;
	CategoriaDAO cDAO;
	Utente loginUser;
	DBConnection dbc;
	LoginFrame lf;
	MainFrame mf;
	RegisterFrame rf;
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
	public void backToLogin()
	{
		rf.setVisible(false);
		lf.setVisible(true);
	}
	public void register()
	{
		try
		{
			dbc = DBConnection.getInstance();
			dbc.getConnection("jdbc:postgresql://localhost/Gestione_Riferimenti_Bibliografici","postgres","admin");
			ResultSet rs = dbc.executeQuery("SELECT MAX(id_utente) FROM utente");
			rs.next();
			rf = new RegisterFrame(this,rs.getInt(1)+1);
			lf.setVisible(false);
			rf.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	public void createUser(int user_ID, String nome, String cognome)
	{
		try
		{
			Utente u = new Utente(nome,cognome,user_ID,new Date(),null);
			UtenteDAO uDAO = new UtenteDAO();
			uDAO.insert(u);
			JOptionPane.showMessageDialog(null,"Utente creato!");
			backToLogin();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
	}
	public boolean login(String user_ID) throws IOException
	{
		try 
		{
			dbc = DBConnection.getInstance();
			dbc.getConnection("jdbc:postgresql://localhost/Gestione_Riferimenti_Bibliografici","postgres","admin"); 
			uDAO = new UtenteDAO();
			loginUser = uDAO.get("SELECT * FROM utente WHERE id_utente ='"+user_ID+"'");
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
	public int retrieveID()
	{
		return loginUser.getUser_ID();
	}

	public List<Riferimento> retrieveRiferimenti(int ID)
	{
		try 
		{
			rDAO = new RiferimentoDAO();
			return rDAO.getAll("SELECT riferimenti_biblio.* FROM riferimenti_biblio NATURAL JOIN autore_riferimento WHERE id_utente ='"+ID+"'");
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
			r.setCategorie(cDAO.getAll("SELECT categoria.* FROM categoria NATURAL JOIN associativa_riferimenti_categoria WHERE id_riferimento='"+r.getId_Rif()+"'"));
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
			r.setAutori(uDAO.getAll("SELECT utente.* FROM utente NATURAL JOIN autore_riferimento WHERE id_riferimento = '"+r.getId_Rif()+"'"));
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
			return rDAO.getAll("SELECT riferimenti_biblio.* FROM riferimenti_biblio JOIN associazione_riferimenti ON riferimenti_biblio.id_riferimento = associazione"
					+ "_riferimenti.id_riferimento AND id_riferimento_associato = '"+r.getId_Rif()+"'");
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public String RetrieveCodiciSottocategorie(String nomeCat)
	{	
		ResultSet rs;
		try
		{
			rs = dbc.executeQuery("SELECT sub_cat("+RetrieveCodiceCategoria(nomeCat)+")");
			rs.next();
			return rs.getString(1);
		} 
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public int RetrieveCodiceCategoria(String nomeCat) throws SQLException
	{	
		ResultSet rs;
		rs = dbc.executeQuery("SELECT id_categoria FROM categoria WHERE descr_categoria = '"+nomeCat+"'");
		rs.next();
		return rs.getInt(1);


	}
	public Object[][] RicercaToObjectMatrix(String testo, String categoria, boolean[] tipi,String filtro)
	{

		try 
		{
			rDAO = new RiferimentoDAO();
			String query = "SELECT DISTINCT riferimenti_biblio.* FROM riferimenti_biblio, associativa_riferimenti_categoria ";
			//condizioni per filtro
			switch(filtro)
			{
			case "Titolo":
				query = query.concat("WHERE titolo_riferimento LIKE '%"+testo+"%' ");
				break;
			case "Autore":
				query = query.concat(", autore_riferimento,utente WHERE autore_riferimento.id_riferimento = riferimenti_biblio.id_riferimento AND autore_riferimento.id_utente = utente.id_utente AND (nome_utente LIKE '%"+testo+"%' OR cognome_utente LIKE '%"+testo+"%') ");
				break;
			case "DOI":
				query = query.concat("WHERE doi = '"+testo+"' ");
				break;
			}
			//condizioni per categorie
			if(!Objects.equals(categoria, "Qualsiasi"))
			{
				query = query.concat("AND associativa_riferimenti_categoria.id_riferimento = riferimenti_biblio.id_riferimento AND associativa_riferimenti_categoria.id_categoria IN ("+RetrieveCodiceCategoria(categoria));
				String sottoCat = RetrieveCodiciSottocategorie(categoria);
				if(!sottoCat.isEmpty())
					query = query.concat(","+sottoCat);
				query = query.concat(") ");
			}
			//condizioni per tipi
			query = query.concat("AND tipo IN ('");
			boolean multipleTypes = false;
			if(tipi[0])
			{
				query = query.concat("Risorsa on-line'");
				multipleTypes = true;
			}
			if(tipi[1])
			{
				if(multipleTypes)
					query = query.concat(",'");
				query = query.concat("Libro'");
				multipleTypes = true;
			}
			if(tipi[2])
			{
				if(multipleTypes)
					query = query.concat(",'");
				query = query.concat("Dataset'");
				multipleTypes = true;
			}
			if(tipi[3])
			{
				if(multipleTypes)
					query = query.concat(",'");
				query = query.concat("Articolo'");
			}
			query = query.concat(")");

			List<Riferimento> risultati = rDAO.getAll(query);
			return RiferimentiToObjectMatrix(risultati,risultati.size());
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}

	}
	public void creaCategoria(String nomeCat,String nomeSuperCat,int user_ID)
	{
		try
		{
			ResultSet rs = dbc.executeQuery("SELECT MAX(id_categoria) FROM categoria");
			rs.next();
			Categoria c;
			if(Objects.isNull(nomeSuperCat))
				c = new Categoria(rs.getInt(1)+1,nomeCat,user_ID);
			else
			{
				c = new Categoria(rs.getInt(1)+1,nomeCat,user_ID,RetrieveCodiceCategoria(nomeSuperCat));
			}

			CategoriaDAO cDAO = new CategoriaDAO();
			cDAO.insert(c);
			JOptionPane.showMessageDialog(null, "Categoria creata!");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
		}
	}
	public String[] getRiferimenti()
	{
		try
		{
			ArrayList<String> arrRiferimenti = new ArrayList<>();

			ResultSet rs = dbc.executeQuery("SELECT * FROM riferimenti_biblio JOIN autore_riferimento ON autore_riferimento.id_riferimento = riferimenti_biblio.id_riferimento WHERE id_utente = "+this.retrieveID());
			while(rs.next())
			{
				arrRiferimenti.add(rs.getString("titolo_riferimento"));
			}
			String[] array = arrRiferimenti.toArray(new String[arrRiferimenti.size()]);
			return array;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public String[] getUtenti()
	{
		try
		{
			ArrayList<String> arrUtenti = new ArrayList<>();

			ResultSet rs = dbc.executeQuery("SELECT * FROM utente");
			while(rs.next())
			{
				arrUtenti.add(rs.getString("nome_utente")+" "+rs.getString("cognome_utente"));
			}
			String[] array = arrUtenti.toArray(new String[arrUtenti.size()]);
			return array;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public String[] getCategorie(boolean b)
	{
		try
		{
			ArrayList<String> arrCategorie = new ArrayList<>();
			if(b)
				arrCategorie.add("Qualsiasi");
			ResultSet rs = dbc.executeQuery("SELECT descr_categoria FROM categoria");
			while(rs.next())
			{
				arrCategorie.add(rs.getString(1));
			}
			String[] array = arrCategorie.toArray(new String[arrCategorie.size()]);
			return array;
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "DB Error:\n"+e.getMessage()+"\nCodice errore: "+e.getErrorCode());
			return null;
		}
	}
	public Object[][] RiferimentiToObjectMatrix(List<Riferimento> listaRiferimenti, int righe)
	{
		Object[][] data = new Object[righe][7];
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
			data[i][3] = listaRiferimenti.get(i).getURL();
			data[i][4] = listaRiferimenti.get(i).getDOI();
			data[i][5] = listaRiferimenti.get(i).categorieToString();
			data[i][6] = listaRiferimenti.get(i).getTipo();
		}
		return data;
	}
	public Object[][] CitazioniToObjectMatrix(List<Riferimento> listaRiferimenti, int righe)
	{
		Object[][] data = new Object[righe][8];
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
				data[fillTemp][3] = citTemp.get(j).getURL();
				data[fillTemp][4] = citTemp.get(j).getDOI();
				data[fillTemp][5] = citTemp.get(j).categorieToString();
				data[fillTemp][6] = citTemp.get(j).getTipo();
				data[fillTemp][7] = listaRiferimenti.get(i).getTitolo();
				fillTemp = fillTemp + 1;
			}
		}
		return data;
	}
}
