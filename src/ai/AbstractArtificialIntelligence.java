package ai;

import game.History;
import game.Move;
import game.PuzzleContext;

public abstract class AbstractArtificialIntelligence {
	protected PuzzleContext context;
	
	public AbstractArtificialIntelligence() {
		
	}
	
	public void setContext(PuzzleContext context) {
		this.context = context;
	}
	
	public abstract History solve();
	
	public abstract Move getNextMove();
	
}
