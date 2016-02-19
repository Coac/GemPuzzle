package element;

public class Tile<T> {
	private T value;
	
	public Tile(T value) {
		this.value = value;
	}
	public T get() {
		return value;
	}
}
