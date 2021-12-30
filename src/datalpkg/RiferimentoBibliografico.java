package datalpkg;

import java.util.ArrayList;
import java.util.Date;

public class RiferimentoBibliografico {
	private String titolo;
	private Date data_creazione;
	private String URL_DOI;
	private Boolean digitale;
	private String descrizione;
	private String tipo;
	private ArrayList<Utente> autori;
	private ArrayList<RiferimentoBibliografico> cita;			//i riferimenti che questo riferimento cita
	private ArrayList<RiferimentoBibliografico> citatoIn;		//i riferimenti in cui questi riferimento viene citato
	private ArrayList<Categoria> categorie;
	

}
