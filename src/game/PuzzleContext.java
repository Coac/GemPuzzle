package game;

import ai.ArtificialIntelligenceInterface;
import element.PuzzleGrid;

public class PuzzleContext<T extends Comparable<T>> {
	
	private PuzzleGrid<T> grid;
	private ArtificialIntelligenceInterface ai;
	
	public PuzzleContext(PuzzleGrid<T> grid) {
		this.grid = grid;
	}
	
	public void setAI(ArtificialIntelligenceInterface ai) {
		this.ai = ai;
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
	public boolean hasWin() {
		for (int i = 0; i < grid.size(); i++) {
			if(i != this.grid.getTile(i).getGoalIndex()) {
				return false;
			}	
		}
		return true;
	}
	
	public void move(Move.MoveDirection move) {
		this.grid.setMove(new Move(move));
	}

}
