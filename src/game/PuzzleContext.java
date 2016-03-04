package game;

import ai.AbstractArtificialIntelligence;
import element.PuzzleGrid;
import game.Move.MoveDirection;

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

	public boolean isSolvable() {
		return this.grid.isSolvable();
	}

	public boolean move(MoveDirection moveDirection) {
		Move move = new Move(moveDirection);
		if (!this.grid.canMove(move)) {
			return false;
		}

		this.grid.move(move);
		history.add(move);
		return true;
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
