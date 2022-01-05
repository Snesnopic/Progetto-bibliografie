package datalpkg;

public class Categoria {
	private String nome;
	private String descrizione;
	private Utente autore;
	private Categoria generaliz;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Utente getAutore() {
		return autore;
	}
	public void setAutore(Utente autore) {
		this.autore = autore;
	}
	public Categoria getGeneraliz() {
		return generaliz;
	}
	public void setGeneraliz(Categoria generaliz) {
		this.generaliz = generaliz;
	}
}
