package ai;

import java.util.Collections;
import java.util.List;
import element.PuzzleGrid;
import game.Move;
import utils.HashMapWithCounters;
import utils.ListWithCounters;
import utils.Pair;

public abstract class AbstractAStarArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {
	private int maxCost = 99999999;

	@Override
	public void silentSolve() {
		HashMapWithCounters<GridState<T>, GridState<T>> parent = new HashMapWithCounters<GridState<T>, GridState<T>>();
		ListWithCounters<GridState<T>> gridStateQueue = new ListWithCounters<GridState<T>>();
		GridState<T> currentState = new GridState<T>(this.grid, 0, null);
		gridStateQueue.add(currentState);

		GridState<T> polledGridState = gridStateQueue.poll();
		while (polledGridState != null) {

			if (polledGridState.getGrid().isSolved()) {
				maxCost = polledGridState.getCost();
				GridState<T> previous = polledGridState;
				while (parent.containsKey(previous)) {
					this.history.addHead(previous.getMove());
					previous = parent.get(previous);
				}

				this.stats.add(parent.getStatistics());
				this.stats.add(gridStateQueue.getStatistics());

			}

			if (polledGridState.getCost() < maxCost) {

				List<Pair<PuzzleGrid<T>, Move>> adjacentsPuzzle = polledGridState.getGrid().getAdjacentPuzzles();

				for (Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {
					int adjacentCost = polledGridState.getCost() + 1 + this.getHeuristic(adjPuzzle.getFirst());
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
							Collections.sort(gridStateQueue);
						}
					} else {
						gridStateQueue.add(existingState);
						Collections.sort(gridStateQueue);
					}

				}
			}

			++this.iterationsNumber;
			polledGridState = gridStateQueue.poll();
		}

	}

	public abstract int getHeuristic(PuzzleGrid<T> puzzle);
}
