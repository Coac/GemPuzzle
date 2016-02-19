package element;

import java.util.ArrayList;
import java.util.List;

public class PuzzleGrid<T> {
	
	private List<Tile<T>> tiles;
	private int size;
	
	public PuzzleGrid(int size) {
		this.size = size;
		tiles = new ArrayList<Tile<T>>();
	}
	
	public Tile<T> getTile(int i, int j) {
		return tiles.get(i%size + j*size);
	}
	
	public T getElement(int i, int j) {
		return getTile(i,j).get();
	}
	
	
	
	private void swap(int i1, int j1, int i2, int j2) {
		Tile<T> temp = this.getTile(i1, j1);
		this.setTile(i1, j1, this.getTile(i2, j2));
		this.setTile(i2, j2, temp);
	}
	
	private void setTile(int i, int j, Tile<T> newTile) {
		tiles.set(i%size + j*size, newTile);
	}

}
