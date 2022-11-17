package ctrlpkg;

import datalpkg.Utente;

public interface UtenteDAO extends DAO<Utente> {
	static UtenteDAO getInstance() {
		return UtenteDAOPostgres.getInstance();
	}

	void closeResource();


}
