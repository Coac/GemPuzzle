package ai;

import element.PuzzleGrid;
import game.History;
import game.Move;

public abstract class AbstractArtificialIntelligence<T> {
	protected PuzzleGrid<T> grid;
	
	public void setGrid(PuzzleGrid<T> grid) {
		this.grid = grid;
	}
	
	public abstract History solve();
	
	public abstract Move getNextMove();
	
}
