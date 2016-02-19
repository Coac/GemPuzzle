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
		int[] indexes = this.grid.getTilesIndexes();
		int pariteVide = 0;
		int nombrePermutations = 0;
		int size = this.grid.size();
		
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				int valeur = indexes[i*size+j];
				if(valeur!= i+j){ 
					indexes[i][j] = indexes[valeur/size][valeur%size];
					//taq[valeur/size][valeur%size]=valeur;
					nombrePermutations++;
				}
			}
		}
		return false;
	}

}
