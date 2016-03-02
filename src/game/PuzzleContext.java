package game;

import ai.AbstractArtificialIntelligence;
import element.PuzzleGrid;

public class PuzzleContext<T> {
	
	private PuzzleGrid<T> grid;
	private AbstractArtificialIntelligence<T> ai;
	
	public PuzzleContext(PuzzleGrid<T> grid) {
		this.grid = grid;
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
			if(i != this.grid.getTile(i).getSortedPosition()) {
				return false;
			}	
		}
		return true;
	}
		
	public boolean isSolvable() {
		return this.grid.isSolvable();
	}
	public void move(Move.MoveDirection move) {
		this.grid.setMove(new Move(move));
	}
	
	public boolean isSolved() {
		return this.grid.isSolved();
	}
	
	@Override
	public String toString() {
		return this.grid.toString();
	}

}
