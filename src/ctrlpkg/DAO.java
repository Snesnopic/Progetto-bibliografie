package ctrlpkg;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	List<T> getAll(String sql) throws SQLException;
	T get(String sql) throws SQLException;
	void update(T obj,String sql);
	void delete(T obj,String sql);
	void insert(T obj,String sql);
	
}
