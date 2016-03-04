package game;

import java.util.LinkedList;
import java.util.List;

public class Move {
	public enum MoveDirection {
		Down, Left, Right, Up
	};

	private MoveDirection move;
	private static List<Move> moveSet;

	public Move(MoveDirection move) {
		this.move = move;
	}

	public MoveDirection get() {
		return this.move;
	}

	@Override
	public String toString() {
		switch (move) {
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MoveDirection) {
			return this.move.equals(obj);
		}
		return super.equals(obj);
	}
	
	public static List<Move> getMoves() {
		if (Move.moveSet == null) {
			Move.moveSet = new LinkedList<Move>();
			Move.moveSet.add(new Move(MoveDirection.Up));
			Move.moveSet.add(new Move(MoveDirection.Left));
			Move.moveSet.add(new Move(MoveDirection.Right));
			Move.moveSet.add(new Move(MoveDirection.Down));
		}
		
		return Move.moveSet;
	}
}
