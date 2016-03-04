package ai;

import java.util.Collections;
import java.util.List;

import utils.HashMapWithCounters;
import utils.ListWithCounters;
import utils.Pair;
import element.PuzzleGrid;
import game.Move;

public abstract class AbstractAStarArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {
	private HashMapWithCounters<GridState<T>, GridState<T>> parents;
	private ListWithCounters<GridState<T>> stateQueue;
	
	public AbstractAStarArtificialIntelligence() {
		this.parents = new HashMapWithCounters<GridState<T>, GridState<T>>();
		this.stateQueue = new ListWithCounters<GridState<T>>();
	}

	public abstract int getHeuristic(PuzzleGrid<T> puzzle);
	
	@Override
	public void silentSolve() {
		GridState<T> currentState = new GridState<T>(this.grid, 0, null);
		this.stateQueue.add(currentState);

		GridState<T> polledState = this.stateQueue.poll();
		while (!polledState.getGrid().isSolved()) {
			List<Pair<PuzzleGrid<T>, Move>> adjacentsPuzzle = polledState.getGrid().getAdjacentPuzzles();

			for (Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {
				if(polledState.getMove() != null && adjPuzzle.getSecond().isInversed(polledState.getMove())) {
					continue;
				}
				
				int adjCost = polledState.getCost() + 1 + this.getHeuristic(adjPuzzle.getFirst());
				GridState<T> adjState = new GridState<T>(adjPuzzle.getFirst(), adjCost, adjPuzzle.getSecond());
				
				if(this.updateParents(adjState, polledState)) {
					this.updateQueue(adjState);
				}
			}

			++this.iterationsNumber;
			polledState = this.stateQueue.poll();
		}
		
		this.saveStatisticsAndHistory(polledState);
	}
	
	private boolean updateParents(GridState<T> currentState, GridState<T> parentState) {
		GridState<T> existingState = this.parents.get(currentState);
		
		if (existingState == null) {
			this.parents.put(currentState, parentState);
			return true;
		}
		
		if (currentState.getCost() < existingState.getCost()) {
			existingState.refreshCost(currentState.getCost());
			existingState.refreshMove(currentState.getMove());
			return true;
		} 
		
		return false;
		
	}
	
	private void updateQueue(GridState<T> state) {
		int index = this.stateQueue.indexOf(state);
		if (index != -1) {
			GridState<T> existingState = this.stateQueue.get(index);
			if (state.getCost() > existingState.getCost()) {
				state.refreshCost(existingState.getCost());
				Collections.sort(this.stateQueue);
			}
		} else {
			this.stateQueue.add(state);
			Collections.sort(this.stateQueue);
		}
	}
	
	private void saveStatisticsAndHistory(GridState<T> lastState) {
		while (this.parents.containsKey(lastState)) {
			this.history.addHead(lastState.getMove());
			lastState = this.parents.get(lastState);
		}
		this.stats.add(this.parents.getStatistics());
		this.stats.add(this.stateQueue.getStatistics());
	}
}
