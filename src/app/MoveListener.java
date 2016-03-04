package app;

import game.Move.MoveDirection;

public interface MoveListener {
	public boolean move(MoveDirection moveDirection);
}
