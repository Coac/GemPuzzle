package ai;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import element.PuzzleGrid;
import game.Move;
import utils.Pair;

public class ASTARArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {
	
	public void silentSolve() {
		HashMap<GridState<T>, GridState<T>> parent = new HashMap<GridState<T>, GridState<T>>();
	  	Queue<GridState<T>> gridStateQueue = new PriorityQueue<GridState<T>>();
		
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

  			int adjacentCost = polledGridState.getCost() + 1;
  			
	  		for(Pair<PuzzleGrid<T>, Move> adjPuzzle : adjacentsPuzzle) {
	  			
	  			GridState<T> adjacentState = new GridState<T>(adjPuzzle.getFirst(), adjacentCost, adjPuzzle.getSecond());
	  			
	  			if(parent.containsKey(adjacentState)) {
	  				GridState<T> existingState = parent.get(adjacentState);
	  					
	  				if(adjacentCost < existingState.getCost()) {
	  					existingState.refreshCost(adjacentCost);
	  					existingState.refreshMove(adjPuzzle.getSecond());

	  					gridStateQueue.add(adjacentState);
	  				}
	  			} else {
	  				parent.put(adjacentState, polledGridState);
  					gridStateQueue.add(adjacentState);
	  			}
	  		}
	  	}
		
	}

}
