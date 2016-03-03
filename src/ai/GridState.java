package ai;

import element.PuzzleGrid;

public class GridState<T> {
	PuzzleGrid<T> grid;
	int cost;
	
	public GridState(PuzzleGrid<T> grid, int cost) {
		this.grid = grid;
		this.cost = cost;
	}
	
	public void refreshCost(int cost) {
		this.cost = cost;
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
