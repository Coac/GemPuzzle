package game;

import ai.AbstractArtificialIntelligence;
import element.PuzzleGrid;

public class PuzzleContext<T> {
	
	private PuzzleGrid<T> grid;
	private AbstractArtificialIntelligence ai;
	
	public PuzzleContext(PuzzleGrid<T> grid) {
		this.grid = grid;
	}
	
	public void setAI(AbstractArtificialIntelligence ai) {
		this.ai = ai;
		this.ai.setContext(this);
	}
	
	public PuzzleGrid<T> getGrid() {
		return this.grid;
	}
	
	public boolean hasWin() {
		for (int i = 0; i < grid.size(); i++) {
			if(i != this.grid.getTile(i).getgoalIndex()) {
				return false;
			}	
		}
		return true;
	}
	
	public void move(Move.MoveDirection move) {
		this.grid.setMove(new Move(move));
	}

}
