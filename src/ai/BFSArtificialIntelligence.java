package ai;

import java.util.List;
import element.PuzzleGrid;
import game.Move;
import utils.HashMapWithCounters;
import utils.ListWithCounters;
import utils.Pair;

public class BFSArtificialIntelligence<T> extends AbstractArtificialIntelligence<T>{

	@Override
	public void silentSolve() {	  	
	  	HashMapWithCounters<GridState<T>, GridState<T>> parent = new HashMapWithCounters<GridState<T>, GridState<T>>();
	  	ListWithCounters<GridState<T>> gridStateQueue = new ListWithCounters<GridState<T>>();
	  	int iterationsNumber=0;	  	
	  	
		GridState<T> currentState = new GridState<T>(this.grid, 0, null);
		gridStateQueue.add(currentState);	
		
	  	while(!gridStateQueue.isEmpty()) {
	  		
	  		GridState<T> polledGridState = gridStateQueue.poll();
	  		
	  		if(polledGridState.getGrid().isSolved()) {
  				System.out.println("WINNNEEEER");
  				GridState<T> previous = polledGridState;
  				while (parent.containsKey(previous)) {
  					this.history.addHead(previous.getMove());
  					previous = parent.get(previous);
  				}
  				return;
  			}	  		
	  		
	  		List<Pair<PuzzleGrid<T>, Move>> adjacentsPuzzle = polledGridState.getGrid().getAdjacentPuzzles();
	  		
	  		for(Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {	  			
	  			GridState<T> adjacentState = new GridState<T>(adjPuzzle.getFirst(), 0, adjPuzzle.getSecond());
	  			if(!parent.containsKey(adjacentState)) {
	  				parent.put(adjacentState, polledGridState);
  					gridStateQueue.add(adjacentState);
	  			}
	  		}
	  		
	  		iterationsNumber++;
	  	}
	}

	@Override
	public String toString() {
		return "BFS Solver";
	}
}
