package game;

import ai.AbstractArtificialIntelligence;
import element.PuzzleGrid;

public class PuzzleContext<T> {

	private PuzzleGrid<T> grid;
	private AbstractArtificialIntelligence<T> ai;
	private History history;

	public PuzzleContext(PuzzleGrid<T> grid) {
		this.grid = grid;
		this.history = new History();
	}

	public void setAI(AbstractArtificialIntelligence<T> ai) {
		this.ai = ai;
		this.ai.setGrid(this.grid);
	}

	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}

	public boolean hasWin() {
		for (int i = 0; i < grid.size(); i++) {
			if (i != this.grid.getTile(i).getSortedPosition()) {
				return false;
			}
		}
		return true;
	}

	public boolean isSolvable() {
		return this.grid.isSolvable();
	}

	public boolean move(Move.MoveDirection move) {
		if (this.grid.setMove(new Move(move))) {
			history.add(new Move(move));
			return true;
		}
		return false;
	}

	public History getHistory() {
		return history;
	}

	public boolean isSolved() {
		return this.grid.isSolved();
	}

	@Override
	public String toString() {
		return this.grid.toString();
	}

}
