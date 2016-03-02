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
		int[] indexes = this.grid.getTilesIndexes();
		int voidParity = 0;
		int permutationsNumber = 0;
		int size = this.grid.size();

		/* First, we calculate the void "parity" */
		int i = 0;
		while (indexes[i] != this.grid.getNullIndex()) {
			i++;
		}
		voidParity = (size - 1) * 2 - i;

		/*
		 * we calculate the number of permutations needed to solve. Based on the
		 * fact that the tiles are indexed from 0 to n*n-1 (with n being the
		 * size). So the tiles indexed k should be at the index k of the
		 * "indexes" array
		 */
		for (i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int value = indexes[i * size + j];
				if (value != i + j) {
					indexes[i * size + j] = indexes[value];
					indexes[value] = value;
					permutationsNumber++;
					j--;
				}
			}
		}
		return ((voidParity % 2) ^ (permutationsNumber % 2)) == 0;
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
