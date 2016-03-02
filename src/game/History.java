package game;

import java.util.ArrayList;
import java.util.List;

public class History {

	private List<Move> moves;

	public History() {
		this.moves = new ArrayList<Move>();
	}

	public void add(Move move) {
		this.moves.add(move);
	}

	public Move get(int i) {
		return this.moves.get(i);
	}

	public Move last() {
		if (moves.size() > 0) {
			return this.moves.get(moves.size() - 1);
		}
		return null;
	}

	public int size() {
		return this.moves.size();
	}

}
