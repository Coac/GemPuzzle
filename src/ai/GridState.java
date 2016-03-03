package ai;

import element.PuzzleGrid;

public class GridState<T> {
	GridState<T> previous;
	PuzzleGrid<T> grid;
	int cost;
	
	public GridState(PuzzleGrid<T> grid, int cost, GridState<T> previous) {
		this.grid = grid;
		this.cost = cost;
		this.previous = previous;
	}
	
	public void refreshCost(int cost) {
		this.cost = cost;
	}
	
	public GridState<T> previous() {
		return this.previous;
	}
	
	public boolean has(PuzzleGrid<T> grid) {
		if (this.grid.equals(grid)) {
			return true;
		}
			
		if (this.previous == null) {
			return false;
		}
		
		return this.previous.has(grid);
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
}
