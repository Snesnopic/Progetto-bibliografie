package datalpkg;

import java.util.Date;
import java.util.List;

public class Utente {
	private String nome;
	private String cognome;
	private int user_ID;
	private Date inizio;
	private Date fine;
	private List<Categoria> categorieCreate;
	private List<Riferimento> riferimentiCreati;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int ID) {
		this.user_ID = ID;
	}
	public List<Categoria> getCategorieCreate() {
		return categorieCreate;
	}
	public void setCategorieCreate(List<Categoria> categorieCreate) {
		this.categorieCreate = categorieCreate;
	}
	public List<Riferimento> getRiferimentiCreati() {
		return riferimentiCreati;
	}
	public void setRiferimentiCreati(List<Riferimento> riferimentiCreati) {
		this.riferimentiCreati = riferimentiCreati;
	}
	public Utente(String nome, String cognome, int ID, List<Categoria> categorieCreate,
			List<Riferimento> riferimentiCreati) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.user_ID = ID;
		this.categorieCreate = categorieCreate;
		this.riferimentiCreati = riferimentiCreati;
	}
	public Utente(String nome, String cognome, int ID,Date inizio,Date fine) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.user_ID = ID;
		this.inizio = inizio;
		this.fine = fine;
	}
	public Utente() {
		// TODO Auto-generated constructor stub
	}
	public String nominativoCompletoToString()
	{
		return this.getNome()+" "+this.getCognome();
	}
	public Date getInizio() {
		return inizio;
	}
	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}
	public Date getFine() {
		return fine;
	}
	public void setFine(Date fine) {
		this.fine = fine;
	}
}
