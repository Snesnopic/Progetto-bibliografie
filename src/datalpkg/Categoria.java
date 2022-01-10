package datalpkg;

public class Categoria {
	private String nome;
	private String descrizione;
	private String autore;
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
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public Categoria getGeneraliz() {
		return generaliz;
	}
	public void setGeneraliz(Categoria generaliz) {
		this.generaliz = generaliz;
	}
	public Categoria(String nome, String descrizione, String autore, Categoria generaliz) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.autore = autore;
		this.generaliz = generaliz;
	}
	public Categoria(String nome, String descrizione, String autore)
	{
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.autore = autore;
	}
}
