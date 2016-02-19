package ai;

import game.History;
import game.Move;
import game.PuzzleContext;

public abstract class AbstractArtificialIntelligence<T> {
	protected PuzzleContext<T> context;
	
	public AbstractArtificialIntelligence() {
		
	}
	
	public void setContext(PuzzleContext<T> context) {
		this.context = context;
	}
	
	public abstract History solve();
	
	public abstract Move getNextMove();
	
}
