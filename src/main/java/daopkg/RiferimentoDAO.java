package daopkg;

import datalpkg.Riferimento;
import java.util.List;

public interface RiferimentoDAO extends DAO<Riferimento> {

	static RiferimentoDAO getInstance() {
		return RiferimentoDAOPostgres.getInstance();
	}

	List<Riferimento> getByUser(int id) throws Exception;
	Riferimento getByName(String name) throws Exception;
	List<Riferimento> getBySearch(String testo, String categoria, boolean[] tipi, String filtro) throws Exception;
	void insert(Riferimento obj, Iterable<Integer> autori, Iterable<Integer> categorie, Iterable<Integer> citazioni) throws Exception;
	void delete(Riferimento r, int user_ID) throws Exception;


}
