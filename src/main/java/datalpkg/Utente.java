package datalpkg;

import java.util.Date;

public class Utente {
	private final String nome;
	private final String cognome;
	private final int user_ID;
	private final Date inizio;
	private final Date fine;

	public Utente(final String nome, final String cognome, final int ID, final Date inizio, final Date fine) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.user_ID = ID;
		this.inizio = inizio;
		this.fine = fine;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public String nominativoCompletoToString() {
		return this.nome + " " + this.cognome;
	}

	public Date getInizio() {
		return inizio;
	}

	public Date getFine() {
		return fine;
	}

}
