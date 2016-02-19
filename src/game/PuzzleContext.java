package game;

import ai.ArtificialIntelligenceInterface;
import element.Grid;

public class PuzzleContext<T> {
	
	private Grid<T> grid;
	private Grid<T> goalGrid;
	private ArtificialIntelligenceInterface ai;
	
	public PuzzleContext(Grid<T> grid, Grid<T> goalGrid) {
		this.grid = grid;
		this.goalGrid = goalGrid;
	}
	
	public void setAI(ArtificialIntelligenceInterface ai) {
		this.ai = ai;
	}
	
	public Grid<T> getGrid() {
		return this.grid;
	}
	public Grid<T> getGoal() {
		return this.grid;
	}
	
	public boolean hasWin() {
		return this.grid.equals(this.goalGrid);
	}

}
