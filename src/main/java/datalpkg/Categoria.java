package datalpkg;

public class Categoria {
	private final int id_Cat;
	private final String nome;
	private final int autore;
	private int id_Generaliz;

	public Categoria(final int id_Cat, final String nome, final int autore, final int generaliz) {
		super();
		this.id_Cat = id_Cat;
		this.nome = nome;
		this.autore = autore;
		this.id_Generaliz = generaliz;
	}

	public Categoria(final int id_Cat, final String nome, final int autore) {
		super();
		this.id_Cat = id_Cat;
		this.nome = nome;
		this.autore = autore;
	}

	public String getNome() {
		return nome;
	}

	public int getAutore() {
		return autore;
	}

	public int getGeneraliz() {
		return id_Generaliz;
	}

	public int getId_Cat() {
		return id_Cat;
	}
}
