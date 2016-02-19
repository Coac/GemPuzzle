package element;

public class Tile<T> {
	private T value;
	private int index;
	
	public Tile(T value, int index) {
		this.value = value;
		this.index = index;
	}
	public T getValue() {
		return value;
	}
	
	public int getIndex() {
		return this.index;
	}
}
