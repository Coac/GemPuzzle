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
		this.ai.setContext(this);
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
	
	/**
	 * Utilise les permutations afin de déterminer si la grille initiale est solvable.
	 */
	public boolean isSolvable(){
		int[] indexes = this.grid.getTilesIndexes();
		int voidIndex = indexes.length-1;
		int voidParity = 0;
		int permutationsNumber = 0;
		int size = this.grid.size();
		
		//First, we calculate the void "parity"
		int i = 0; 
		while(indexes[i] != voidIndex){
			 i++;
		}
		voidParity = (size-1)*2 - i;
		
		//we calculate the number of permutations needed
		for(i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				int valeur = indexes[i*size+j];
				if(valeur!= i+j){ 
					indexes[i*size+j] = indexes[valeur];
					indexes[valeur]=valeur;
					permutationsNumber++;
					j--;
				}
			}
		}
		return ((voidParity%2)^(permutationsNumber%2)) == 0;
	}	
	
	public boolean move(Move.MoveDirection move) {
		return this.grid.setMove(new Move(move));
	}
	
	public boolean isSolved() {
		return this.grid.isSolved();
	}
	
	@Override
	public String toString() {
		return this.grid.toString();
	}

}
