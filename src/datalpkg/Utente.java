package datalpkg;

import java.util.List;

public class Utente {
	private String nome;
	private String cognome;
	private String cf;
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
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
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
	public Utente(String nome, String cognome, String cf, List<Categoria> categorieCreate,
			List<Riferimento> riferimentiCreati) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.categorieCreate = categorieCreate;
		this.riferimentiCreati = riferimentiCreati;
	}
	public Utente(String nome, String cognome, String cf) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
	}
	public Utente() {
		// TODO Auto-generated constructor stub
	}
	public String nominativoCompletoToString()
	{
		return this.getNome()+" "+this.getCognome();
	}
}
