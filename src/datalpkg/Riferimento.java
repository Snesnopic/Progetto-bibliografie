package datalpkg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Riferimento {
	private int id_Rif;
	private String titolo;
	private Date dataCreazione;
	private String tipo;
	private String URL;
	private Integer DOI;
	private Boolean digitale;
	private String descrizione;
	private String descr_autore;
	private List<Categoria> categorie;
	private List<Utente> autori;
	private List<Riferimento> cited;
	private List<Riferimento> citedIn;

	public Riferimento(int id_Rif, String titolo, Date dataCreazione, String tipo, String URL, Integer DOI,
			Boolean digitale, String descrizione, String descr_autore) {
		super();
		this.id_Rif = id_Rif;
		categorie = new ArrayList<>();
		this.titolo = titolo;
		this.dataCreazione = dataCreazione;
		this.DOI = DOI;
		this.tipo = tipo;
		this.URL = URL;
		this.digitale = digitale;
		this.descrizione = descrizione;
		this.descr_autore = descr_autore;
	}

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

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public Boolean getDigitale() {
		return digitale;
	}

	public void setDigitale(Boolean digitale) {
		this.digitale = digitale;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

	public List<Utente> getAutori() {
		return autori;
	}

	public void setAutori(List<Utente> autori) {
		this.autori = autori;
	}

	public List<Riferimento> getCitazioni() {
		return cited;
	}

	public void setCitazioni(List<Riferimento> citazioni) {
		this.cited = citazioni;
	}

	public String citazioniToString() {
		String a = "";
		for (int i = 0; i < cited.size(); i++) {
			a = a.concat(cited.get(i).getTitolo());
			if (i != cited.size() - 1)
				a = a.concat(",");
		}
		return a;
	}

	public String citazioniToString(int ID) {
		String a = "";
		for (int i = 0; i < cited.size(); i++) {
			List<Utente> tempList = cited.get(i).getAutori();
			if (tempList != null) {
				for (int j = 0; j < tempList.size(); j++) {
					if (ID == tempList.get(i).getUser_ID()) {
						a = a.concat(cited.get(i).getTitolo());
						if (i != cited.size() - 1)
							a = a.concat(",");
						j = tempList.size();
					}
				}
			}
		}
		return a;
	}

	public String autoriToString() {
		String a = "";
		for (int i = 0; i < autori.size(); i++) {
			a = a.concat(autori.get(i).nominativoCompletoToString());
			if (i != autori.size() - 1)
				a = a.concat(",");
		}
		return a;
	}

	public String categorieToString() {
		String a = "";
		for (int i = 0; i < categorie.size(); i++) {
			a = a.concat(categorie.get(i).getNome() + " ");
			if (i != categorie.size() - 1)
				a = a.concat(",");
		}
		return a;
	}

	public List<Riferimento> getCitedIn() {
		return citedIn;
	}

	public void setCitedIn(List<Riferimento> citedIn) {
		this.citedIn = citedIn;
	}

	public int getId_Rif() {
		return id_Rif;
	}

	public void setId_Rif(int id_Rif) {
		this.id_Rif = id_Rif;
	}

	public Integer getDOI() {
		return DOI;
	}

	public void setDOI(int dOI) {
		DOI = dOI;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescr_autore() {
		return descr_autore;
	}

	public void setDescr_autore(String descr_autore) {
		this.descr_autore = descr_autore;
	}
}