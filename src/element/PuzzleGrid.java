package element;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Move.MoveDirection;

public class PuzzleGrid<T> {

	private List<Tile<T>> tiles;
	private int size;
	private int nullIndex;

	public PuzzleGrid(int size, List<Tile<T>> tiles) {
		this(size, tiles, size * size - 1);
	}

	public PuzzleGrid(int size, List<Tile<T>> tiles, int nullIndex) {
		this.size = size;
		this.tiles = tiles;
		this.nullIndex = nullIndex;
	}
	
	@SuppressWarnings("unchecked")
	public PuzzleGrid<T> clone() {
		return new PuzzleGrid<T>(this.size, (List<Tile<T>>)((ArrayList<Tile<T>>) this.tiles).clone(), this.nullIndex);
	}

	public int size() {
		return this.size;
	}

	public int getNbTiles() {
		return this.tiles.size();
	}

	public Tile<T> getTile(int i, int j) {
		return this.tiles.get(i % size + j * size);
	}

	public Tile<T> getTile(int i) {
		return tiles.get(i);
	}

	public T getElement(int i, int j) {
		return this.getTile(i, j).getValue();
	}

	public boolean setMove(Move move) {
		switch (move.get()) {
		case Down:
			if (nullIndex + size >= getNbTiles())
				return false;
			swapIndex(nullIndex, nullIndex + size);
			break;
		case Left:
			if (nullIndex % size - 1 < 0)
				return false;
			swapIndex(nullIndex, nullIndex - 1);
			break;
		case Right:
			if (nullIndex % size + 1 >= size)
				return false;
			swapIndex(nullIndex, nullIndex + 1);
			break;
		case Up:
			if (nullIndex - size < 0)
				return false;
			swapIndex(nullIndex, nullIndex - size);
			break;
		default:
			return false;
		}
		return true;
	}

	public int[] getTilesIndexes() {
		int[] indexes = new int[this.getNbTiles()];
		for (int i = 0; i < this.getNbTiles(); i++) {
			indexes[i] = this.tiles.get(i).getSortedPosition();
		}
		return indexes;
	}

	private void swapIndex(int index1, int index2) {
		Tile<T> temp = this.tiles.get(index1);
		this.tiles.set(index1, this.tiles.get(index2));
		this.tiles.set(index2, temp);
		nullIndex = index2;
	}

	private void swapCoord(int i1, int j1, int i2, int j2) {
		Tile<T> temp = this.getTile(i1, j1);
		this.setTile(i1, j1, this.getTile(i2, j2));
		this.setTile(i2, j2, temp);
	}

	private void setTile(int i, int j, Tile<T> newTile) {
		this.tiles.set(i % size + j * size, newTile);
	}

	public int getNullIndex() {
		return nullIndex;
	}

	public boolean isSolved() {
		for (int i = 0; i < this.getNbTiles(); i++) {
			if (this.tiles.get(i).getSortedPosition() != i) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Utilise les permutations afin de déterminer si la grille initiale est solvable.
	 */
	public boolean isSolvable(){
		int[] indexes = this.getTilesIndexes();
		int voidIndex = indexes.length-1;
		int voidParity = 0;
		int permutationsNumber = 0;
		int size = this.size();
		
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
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.getNbTiles(); i++) {
			if (i % this.size() == 0) {
				str += "\n";
			}
			str += tiles.get(i).getValue().toString() + "\t";

		}
		return str;
	}

	public List<PuzzleGrid<T>> getAdjacentPuzzles() {
		List<PuzzleGrid<T>> list = new ArrayList<PuzzleGrid<T>>();
		
		PuzzleGrid<T> puzzleUp = this.clone();
		if(puzzleUp.setMove(new Move(MoveDirection.Up))) {
			list.add(puzzleUp);
		}
		PuzzleGrid<T> puzzleLeft = this.clone();
		if(puzzleLeft.setMove(new Move(MoveDirection.Left))) {
			list.add(puzzleLeft);
		}
		PuzzleGrid<T> puzzleDown = this.clone();
		if(puzzleDown.setMove(new Move(MoveDirection.Down))) {
			list.add(puzzleDown);
		}
		PuzzleGrid<T> puzzleRight = this.clone();
		if(puzzleRight.setMove(new Move(MoveDirection.Right))) {
			list.add(puzzleRight);
		}
		return list;
	}

	public int estimateError() {
		int count = 0;
		for (int i = 0; i < this.getNbTiles(); i++) {
			if(this.tiles.get(i).getSortedPosition() != i) {
				count++;
			}
		}
		return count;
	}
}
