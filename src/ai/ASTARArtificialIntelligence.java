package ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import element.PuzzleGrid;
import game.History;
import game.Move;

public class ASTARArtificialIntelligence<T> extends AbstractArtificialIntelligence<T> {

	@Override
	public History solve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move getNextMove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<PuzzleGrid<T>> aStarSolveMine(PuzzleGrid<T> puzzle) {
		HashMap<GridState<T>, GridState<T>> parent = new HashMap<GridState<T>, GridState<T>>();
	  	Queue<GridState<T>> gridStateQueue = new LinkedList<GridState<T>>();
		
		GridState<T> currentState = new GridState<T>(puzzle, 0);
		gridStateQueue.add(currentState);
	  	
	  	while(!gridStateQueue.isEmpty()) {
	  		GridState<T> polledGridState = gridStateQueue.poll();
	  		
	  		
	  		if(polledGridState.getGrid().isSolved()) {
  				System.out.println("WINNNEEEER");
  				
  				GridState<T> previous = polledGridState;
  				while (parent.containsKey(previous)) {
  					System.out.println(previous.getGrid());
  					previous = parent.get(previous);
  				}
  				return null;
  			}
	  		
	  		
	  		List<PuzzleGrid<T>> adjacentsPuzzle = polledGridState.getGrid().getAdjacentPuzzles();

  			int adjacentCost = polledGridState.getCost() + 1;
  			
	  		for(PuzzleGrid<T> adjPuzzle : adjacentsPuzzle) {
	  			GridState<T> adjacentState = new GridState<T>(adjPuzzle, adjacentCost);
	  			
	  			if(parent.containsKey(adjacentState)) {
	  				GridState<T> existingState = parent.get(adjacentState);
	  		
	  				if(adjacentCost < existingState.getCost()) {
	  					existingState.refreshCost(adjacentCost);
			  			
	  					gridStateQueue.add(adjacentState);
	  				}
	  			} else {
	  				parent.put(adjacentState, polledGridState);
  					gridStateQueue.add(adjacentState);
	  			}
	  				  			
	  			
	  		}
	  	}
		
		
		
		return null;
	}

}
