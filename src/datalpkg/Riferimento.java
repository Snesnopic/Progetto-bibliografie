package datalpkg;

import java.util.ArrayList;
import java.util.Date;

public class Riferimento {
	private String titolo;
	private Date dataCreazione;
	private String tipo;
	private String DOI_URL;
	private Boolean digitale;
	private ArrayList<Categoria> categorie;
	private ArrayList<Utente> autori;
	private ArrayList<Riferimento> citazioni;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDOI_URL() {
		return DOI_URL;
	}
	public void setDOI_URL(String dOI_URL) {
		DOI_URL = dOI_URL;
	}
	public Boolean getDigitale() {
		return digitale;
	}
	public void setDigitale(Boolean digitale) {
		this.digitale = digitale;
	}
	public ArrayList<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<Categoria> categorie) {
		this.categorie = categorie;
	}
	public ArrayList<Utente> getAutori() {
		return autori;
	}
	public void setAutori(ArrayList<Utente> autori) {
		this.autori = autori;
	}
	public ArrayList<Riferimento> getCitazioni() {
		return citazioni;
	}
	public void setCitazioni(ArrayList<Riferimento> citazioni) {
		this.citazioni = citazioni;
	}
}