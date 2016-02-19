package game;

import ai.ArtificialIntelligenceInterface;
import element.PuzzleGrid;

public class PuzzleContext<T> {
	
	private PuzzleGrid<T> grid;
	private PuzzleGrid<T> goalGrid;
	private ArtificialIntelligenceInterface ai;
	
	public PuzzleContext(PuzzleGrid<T> grid, PuzzleGrid<T> goalGrid) {
		this.grid = grid;
		this.goalGrid = goalGrid;
	}
	
	public void setAI(ArtificialIntelligenceInterface ai) {
		this.ai = ai;
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
	public PuzzleGrid<T> getGoal() {
		return this.grid;
	}
	
	public boolean hasWin() {
		return this.grid.equals(this.goalGrid);
	}

}
