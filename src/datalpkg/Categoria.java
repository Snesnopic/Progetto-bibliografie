package datalpkg;

public class Categoria {
	private int id_Cat;
	private String nome;
	private int autore;
	private int id_Generaliz;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getAutore() {
		return autore;
	}
	public void setAutore(int autore) {
		this.autore = autore;
	}
	public int getGeneraliz() {
		return id_Generaliz;
	}
	public void setGeneraliz(int generaliz) {
		this.id_Generaliz = generaliz;
	}
	
	public Categoria(int id_Cat,String nome, int autore, int generaliz) {
		super();
		this.id_Cat = id_Cat;
		this.nome = nome;
		this.autore = autore;
		this.id_Generaliz = generaliz;
	}
	public Categoria(int id_Cat,String nome, int autore)
	{
		super();
		this.id_Cat = id_Cat;
		this.nome = nome;
		this.autore = autore;
		this.id_Generaliz = 0;
	}
	public int getId_Cat() {
		return id_Cat;
	}
	public void setId_Cat(int id_Cat) {
		this.id_Cat = id_Cat;
	}
}
