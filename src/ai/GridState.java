package ai;

import element.PuzzleGrid;
import game.Move;

public class GridState<T> {
	PuzzleGrid<T> grid;
	int cost;
	Move move;
	
	public GridState(PuzzleGrid<T> grid, int cost, Move move) {
		this.grid = grid;
		this.cost = cost;
		this.move = move;
	}
	
	public void refreshCost(int cost) {
		this.cost = cost;
	}
	
	public void refreshMove(Move move) {
		this.move = move;
	}
	
	public Move getMove() {
		return this.move;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GridState)) {
			return false;
		}
		
		return ((GridState) obj).getGrid().equals(this.grid);
	}
}
