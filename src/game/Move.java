package game;

public class Move {
	public enum MoveDirection { Down, Left, Right, Up};
	
	private MoveDirection move;
	
	public Move(MoveDirection move) {
		this.move = move;
	}
	
	public MoveDirection get() {
		return this.move;
	}
	
	@Override
	public String toString() {
		switch(move) {
		case Down:
			return "↓";
		case Left:
			return "←";
		case Right:
			return "→";
		case Up:
			return "↑";
		default:
			return "";
		}
	}
}
