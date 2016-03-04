package ai;

import element.PuzzleGrid;

public class AStarManhattanArtificialIntelligence<T> extends AbstractAStarArtificialIntelligence<T> {
	
	
	public int getHeuristic(PuzzleGrid<T> puzzle) {
		int sumDistance = 0;
		for (int i = 0; i < puzzle.getNbTiles(); i++) {
			int size = puzzle.size();
			int x1 = i % size;
			int y1 = i / size;
			
			int goalIndex = puzzle.getTile(i).getSortedPosition();
			int x2 = goalIndex % size;
			int y2 = goalIndex / size;
			
			sumDistance += Math.abs(x1 - x2) + Math.abs(y1 - y2);
		}
		
		return sumDistance;
	}

	
	@Override
	public String toString() {
		return "A* Algorithm Manhattan";
	}

}
