package ctrlpkg;

import datalpkg.Categoria;

public interface CategoriaDAO extends DAO<Categoria> {

	static CategoriaDAO getInstance() {
		 return CategoriaDAOPostgres.getInstance();
	}

	String getSubCatCodes(String nomeCat) throws Exception;
	Categoria getByName(String nomeCat) throws Exception;

}
