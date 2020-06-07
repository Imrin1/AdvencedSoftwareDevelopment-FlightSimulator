package server_side;

import java.util.ArrayList;
import java.util.List;

public interface Searcher<T> {
	public ArrayList<State<T>> search(Searchable s);
}
