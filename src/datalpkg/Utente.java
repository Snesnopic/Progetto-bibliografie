package datalpkg;

import java.util.ArrayList;

public class Utente {
	private String nome;
	private String cognome;
	private String cf;
	private ArrayList<Categoria> categorieCreate;
	private ArrayList<Riferimento> riferimentiCreati;
	
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
	public ArrayList<Categoria> getCategorieCreate() {
		return categorieCreate;
	}
	public void setCategorieCreate(ArrayList<Categoria> categorieCreate) {
		this.categorieCreate = categorieCreate;
	}
	public ArrayList<Riferimento> getRiferimentiCreati() {
		return riferimentiCreati;
	}
	public void setRiferimentiCreati(ArrayList<Riferimento> riferimentiCreati) {
		this.riferimentiCreati = riferimentiCreati;
	}
}
