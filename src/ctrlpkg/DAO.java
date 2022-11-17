package ctrlpkg;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	List<T> getAll() throws SQLException;

	T get(int id) throws SQLException;

	void update(T obj) throws SQLException;

	void insert(T obj) throws SQLException;

}
