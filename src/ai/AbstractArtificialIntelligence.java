package ai;

import element.PuzzleGrid;
import game.History;
import game.Move;

public abstract class AbstractArtificialIntelligence<T> {
	protected PuzzleGrid<T> grid;
	protected History history;
	protected int currentMove;
	
	public AbstractArtificialIntelligence() {
		this.reset();
	}
	
	public void setGrid(PuzzleGrid<T> grid) {
		this.grid = grid;
		this.reset();
	}

	public History solve() {
		this.reset();
		this.history = new History();
		this.silentSolve();
		
		return this.history;
	}

	public Move getNextMove() {
		if (this.history == null) {
			this.solve();
		}
		
		return this.history.get(this.currentMove++);
	}
	
	private void reset() {
		this.history = null;
		this.currentMove = 0;
	}
	protected abstract void silentSolve();
	
	public abstract String toString();
}
