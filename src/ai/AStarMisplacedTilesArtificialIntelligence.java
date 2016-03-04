package ai;

import element.PuzzleGrid;

public class AStarMisplacedTilesArtificialIntelligence<T> extends AbstractAStarArtificialIntelligence<T> {
	
	
	public int getHeuristic(PuzzleGrid<T> puzzle) {
		return puzzle.getNbMisplacedTiles();
	}

	
	@Override
	public String toString() {
		return "A* Algorithm MisplacedTiles";
	}

}
