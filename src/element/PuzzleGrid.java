package element;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Move.MoveDirection;
import utils.Pair;

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
	
	public boolean canMove(Move move) {
		switch (move.get()) {
		case Down:
			return !(nullIndex + size >= getNbTiles());
		case Left:
			return !(nullIndex % size - 1 < 0);
		case Right:
			return !(nullIndex % size + 1 >= size);
		case Up:
			return !(nullIndex - size < 0);
		default:
			return false;
		}
	}
	
	public void move(Move move) {
		if (!this.canMove(move)) {
			return;
		}
		
		switch (move.get()) {
		case Down:
			 this.swapIndex(nullIndex, nullIndex + size);
			 break;
		case Left:
			this.swapIndex(nullIndex, nullIndex - 1);
			break;
		case Right:
			this.swapIndex(nullIndex, nullIndex + 1);
			break;
		case Up:
			this.swapIndex(nullIndex, nullIndex - size);
			break;
		default:
		}
	}
	
	/**
	 * @deprecated Use canMove followed by move instead.
	 */
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

	public int getTileIndex(Tile<T> t){
		return this.tiles.indexOf(t);
	}
	
	public int[] getTilesIndexes() {
		int[] indexes = new int[this.getNbTiles()];
		for (int i = 0; i < this.getNbTiles(); i++) {
			indexes[i] = this.tiles.get(i).getSortedPosition();
		}
		return indexes;
	}

	public void swapIndex(int index1, int index2) {
		Tile<T> temp = this.tiles.get(index1);
		this.tiles.set(index1, this.tiles.get(index2));
		this.tiles.set(index2, temp);
		
		if(index1 == nullIndex){
			nullIndex = index2;
		}else if(index2 == nullIndex){
			nullIndex = index1;
		}
	}

	public void swapCoord(int i1, int j1, int i2, int j2) {
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
	
	public boolean isSolvable(){
		int[] indexes = this.getTilesIndexes();
		int voidParity = 0;
		int voidIndex = this.getTile(this.nullIndex).getSortedPosition();
		int permutationsNumber = 0;
		
		//First, we calculate the void "parity"
		int i = 0; 
		while(indexes[i] != voidIndex){
			 i++;
		}
		/*
		voidParity = (size-1)*2 - i;
		*/
		voidParity = (voidIndex / this.size) + (voidIndex % this.size) - i/this.size - i%size;
		//need positive integer
		if(voidParity < 0)
			voidParity = -voidParity;
	
		for(i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				int valeur = indexes[i*this.size+j];
				if(valeur!= i*size+j){ 
					indexes[i*this.size+j] = indexes[valeur];
					indexes[valeur]=valeur;
					permutationsNumber++;
					j--;
				}
			}
		}
		return ((voidParity % 2)^(permutationsNumber % 2)) == 0;
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

	public List<Pair<PuzzleGrid<T>, Move>> getAdjacentPuzzles() {
		List<Pair<PuzzleGrid<T>, Move>> list = new ArrayList<Pair<PuzzleGrid<T>, Move>>();
		
		for (Move move : Move.getMoves()) {
			PuzzleGrid<T> puzzleMove = this.clone();
			puzzleMove.move(move);
			list.add(new Pair<PuzzleGrid<T>, Move>(puzzleMove, move));
		}

		return list;
	}

	public int getNbMisplacedTiles() {
		int count = 0;
		for (int i = 0; i < this.getNbTiles(); i++) {
			if(this.tiles.get(i).getSortedPosition() != i) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			  return false;
		if (!(obj instanceof PuzzleGrid))
		  return false;
		
		PuzzleGrid<T> puzzle = (PuzzleGrid<T>) obj;
		for (int i = 0; i < this.getNbTiles(); i++) {
			if(!this.getTile(i).equals(puzzle.getTile(i))) {
				return false;
			}
		}
			
			
		return true;
	}
	
	
}
