package ai;

import element.PuzzleGrid;


public class BFSArtificialIntelligence<T> extends AbstractAStarArtificialIntelligence<T>{

	@Override
	public int getHeuristic(PuzzleGrid<T> puzzle) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "BFS Solver";
	}

	
}
