package ai;

import java.util.Collections;
import java.util.List;

import utils.HashMapWithCounters;
import utils.ListWithCounters;
import utils.Pair;
import element.PuzzleGrid;
import game.Move;

public class DFSArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {
	
	private int maxNode = 100;
	@Override
	public void silentSolve() {
		HashMapWithCounters<GridState<T>, GridState<T>> parent = new HashMapWithCounters<GridState<T>, GridState<T>>();
		ListWithCounters<GridState<T>> gridStateQueue = new ListWithCounters<GridState<T>>();
		GridState<T> currentState = new GridState<T>(this.grid, 0, null);
		gridStateQueue.add(currentState);

		GridState<T> polledGridState = gridStateQueue.poll();
		while (!polledGridState.getGrid().isSolved()) {
			if(polledGridState.getCost() < maxNode) {
				List<Pair<PuzzleGrid<T>, Move>> adjacentsPuzzle = polledGridState.getGrid().getAdjacentPuzzles();
	
				for (Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {
					if(polledGridState.getMove() != null && adjPuzzle.getSecond().isInversed(polledGridState.getMove())) {
						continue;
					}
					
					int adjacentCost = polledGridState.getCost() + 1;
					GridState<T> adjacentState = new GridState<T>(adjPuzzle.getFirst(), adjacentCost,
							adjPuzzle.getSecond());
	
					GridState<T> existingState = parent.get(adjacentState);
					if (existingState != null && adjacentCost < existingState.getCost()) {
						existingState.refreshCost(adjacentCost);
						existingState.refreshMove(adjPuzzle.getSecond());
					} else {
						existingState = adjacentState;
						parent.put(existingState, polledGridState);
					}
	
					int index = gridStateQueue.indexOf(existingState);
					if (index >= 0) {
						GridState<T> state = gridStateQueue.get(index);
						if (state.getCost() > existingState.getCost()) {
							state.refreshCost(existingState.getCost());
							Collections.sort(gridStateQueue, Collections.reverseOrder());
						}
					} else {
						gridStateQueue.add(existingState);
						Collections.sort(gridStateQueue, Collections.reverseOrder());
					}
	
				}
			}

			++this.iterationsNumber;
			polledGridState = gridStateQueue.poll();
		}

		GridState<T> previous = polledGridState;
		while (parent.containsKey(previous)) {
			this.history.addHead(previous.getMove());
			previous = parent.get(previous);
		}
		this.stats.add(parent.getStatistics());
		this.stats.add(gridStateQueue.getStatistics());
	}
	
	@Override
	public String toString() {
		return "DFS Solver";
	}

}
