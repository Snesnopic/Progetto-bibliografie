package ctrlpkg;

import java.util.List;

public interface DAO<T> {
	List<T> getAll();
	T get(int index);
	void update(T obj);
	void delete(T obj);
}
