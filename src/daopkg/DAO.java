package daopkg;

import datalpkg.Riferimento;
import java.util.List;

public interface DAO<T> {
	List<T> getAll() throws Exception;

	T get(int id) throws Exception;

	List<T> getByRif(Riferimento r) throws Exception;

	int getNextId() throws Exception;

	void update(T obj) throws Exception;

	void insert(T obj) throws Exception;

}
