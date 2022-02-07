package ctrlpkg;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	List<T> getAll(String sql) throws SQLException;
	T get(String sql) throws SQLException;
	void update(T obj) throws SQLException;
	void delete(T obj) throws SQLException;
	void insert(T obj) throws SQLException;
	
}
