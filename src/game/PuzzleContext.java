package game;

import ai.ArtificialIntelligenceInterface;
import element.PuzzleGrid;

public class PuzzleContext<T> {
	
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
			if(i != this.grid.getTile(i).getgoalIndex()) {
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * Utilise les permutations afin de déterminer si la grille initiale est solvable.
	 */
	public boolean isSolvable(){
		int nombrePermutations = 0;
		for(int i = 0; i < this.grid.nbTiles(); i++){
			if(this.grid.getElement(, j)){
				
			}
		}
		return false;
	}

}
