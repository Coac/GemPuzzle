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
		GridState<T> previous = new GridState<>(puzzle, 0, null);
	  	Queue<PuzzleGrid<T>> puzzleQueue = new LinkedList<PuzzleGrid<T>>();
	  	Queue<Integer> puzzleQueueCost = new LinkedList<Integer>();
	  	
	  	
	  	puzzleQueue.add(puzzle);
	  	puzzleQueueCost.add(0);
	  	
	  	while(!puzzleQueue.isEmpty()) {
	  		
	  		PuzzleGrid<T> popedPuzzle = puzzleQueue.poll();
	  		int popedPuzzleCost = puzzleQueueCost.poll();
	  		
	  		List<PuzzleGrid<T>> adjacentsPuzzle = popedPuzzle.getAdjacentPuzzles();
	  		
	  		for(PuzzleGrid<T> adjPuzzle : adjacentsPuzzle) {
	  			
	  			
	  			int adjPuzzleCost =  popedPuzzleCost +1;
	  			
	  			if(previous.has(adjPuzzle)) {
	  				
	  				if(adjPuzzleCost < previous.getCost()) {
  						previous.refreshCost(adjPuzzleCost);
			  			
			  			puzzleQueue.add(adjPuzzle);
			  			puzzleQueueCost.add(popedPuzzleCost);
	  				}
	  				
	  			} else {

	  				previous = new GridState<T>(popedPuzzle, adjPuzzleCost, previous);
	  			
		  			puzzleQueue.add(adjPuzzle);
		  			puzzleQueueCost.add(popedPuzzleCost);
	  			}
	  			
	  			
	  			
	  			if(adjPuzzle.isSolved()) {
	  				System.out.println("WINNNEEEER");
	  				
	  				while (previous.previous() != null) {
	  					System.out.println(previous.previous().getGrid());
	  					previous = previous.previous();
	  				}
	  				return null;
	  			}
	  			
	
	  		}
	  	}
		
		
		
		return null;
	}

}
