package element;

public class Tile<T> {
	private T value;
	private int goalIndex;
	
	public Tile(T value, int goalIndex) {
		this.value = value;
		this.goalIndex = goalIndex;
	}
	public T getValue() {
		return value;
	}
	
	public int getgoalIndex() {
		return this.goalIndex;
	}
}
