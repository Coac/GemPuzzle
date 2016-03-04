package ai;

import element.PuzzleGrid;

public class AStarOutRowAndColumnArtificialIntelligence<T> extends AbstractAStarArtificialIntelligence<T> {
	
	
	public int getHeuristic(PuzzleGrid<T> puzzle) {
		int sumNbOutRowAndColumns = 0;
		for (int i = 0; i < puzzle.getNbTiles(); i++) {
			int size = puzzle.size();
			int x1 = i % size;
			int y1 = i / size;
			
			int goalIndex = puzzle.getTile(i).getSortedPosition();
			int x2 = goalIndex % size;
			int y2 = goalIndex / size;
			
			sumNbOutRowAndColumns += (x1==x2 ? 0 : 1);
			sumNbOutRowAndColumns += (y1==y2 ? 0 : 1);
		}
		return sumNbOutRowAndColumns;
	}

	
	@Override
	public String toString() {
		return "A* Algorithm OutRowColumn";
	}

}
