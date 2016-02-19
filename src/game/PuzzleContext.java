package game;

import ai.ArtificialIntelligenceInterface;
import element.Grid;

public class PuzzleContext<T> {
	
	private Grid<T> grid;
	private ArtificialIntelligenceInterface ai;
	
	public PuzzleContext(Grid<T> grid) {
		this.grid = grid;
	}
	
	public void setAI(ArtificialIntelligenceInterface ai) {
		this.ai = ai;
	}

}
