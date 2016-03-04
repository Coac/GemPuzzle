package ai;

import java.util.ArrayList;

import utils.DataStructStats;
import element.PuzzleGrid;
import game.History;
import game.Move;

public abstract class AbstractArtificialIntelligence<T> {
	protected PuzzleGrid<T> grid;
	protected History history;
	protected int currentMove;
	protected ArrayList<DataStructStats> stats;
	protected int iterationsNumber;

	public AbstractArtificialIntelligence() {
		this.reset();
		this.stats = new ArrayList<DataStructStats>();
	}
	
	protected abstract void silentSolve();

	@Override
	public abstract String toString();

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
		this.stats = new ArrayList<DataStructStats>();
		this.iterationsNumber = 0;

	}

	/**
	 * Return the add count, remove count and maximum size of each data
	 * structure as objects
	 * 
	 * @return the statistics of each data structure
	 * @see DataStructStats
	 */
	public ArrayList<DataStructStats> getStatistics() {
		return this.stats;
	}

	public int getIterationsNumber() {
		return this.iterationsNumber;
	}
}
