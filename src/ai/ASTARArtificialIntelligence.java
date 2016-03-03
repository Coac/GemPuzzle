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
	  	HashMap<PuzzleGrid<T>,PuzzleGrid<T>> history = new HashMap<PuzzleGrid<T>,PuzzleGrid<T>>();
	  	HashMap<PuzzleGrid<T>, Integer> cost = new HashMap<PuzzleGrid<T>, Integer>();

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
	  			
	  			if(history.containsKey(adjPuzzle)) {
	  				
	  				if(adjPuzzleCost < cost.get(adjPuzzle)) {
	  					cost.remove(adjPuzzle);
	  					history.remove(adjPuzzle);
	  					
	  					history.put(adjPuzzle, popedPuzzle);
			  			cost.put(adjPuzzle, adjPuzzleCost);
			  			
			  			puzzleQueue.add(adjPuzzle);
			  			puzzleQueueCost.add(popedPuzzleCost);
	  				}
	  				
	  			} else {
	  				history.put(adjPuzzle, popedPuzzle);
		  			cost.put(adjPuzzle, adjPuzzleCost);
		  			
		  			puzzleQueue.add(adjPuzzle);
		  			puzzleQueueCost.add(popedPuzzleCost);
	  			}
	  			
	  			
	  			
	  			if(adjPuzzle.isSolved()) {
	  				System.out.println("WINNNEEEER");
	  				
	  				PuzzleGrid<T> searchPuzzlePredecessor = adjPuzzle;
	  				while(history.containsKey(searchPuzzlePredecessor)) {
	  					searchPuzzlePredecessor = history.get(searchPuzzlePredecessor);
	  					System.out.println(searchPuzzlePredecessor);
	  				}
	  				return null;
	  			}
	  			
	
	  		}
	  	}
		
		
		
		return null;
	}

}
