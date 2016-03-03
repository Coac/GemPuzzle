package ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import element.PuzzleGrid;
import game.History;
import game.Move;

public class BFSArtificialIntelligence<T> extends AbstractArtificialIntelligence<T>{

	@Override
	public History solve() {
	  	HashMap<PuzzleGrid<T>,PuzzleGrid<T>> history = new HashMap<PuzzleGrid<T>,PuzzleGrid<T>>();
	  	LinkedList<PuzzleGrid<T>> puzzleQueue = new LinkedList<PuzzleGrid<T>>();
	  	//counter (other counters should be implemented : add/remove of each strucutres + max size)
		int iterationsNumber=0;
		
	  	while(!puzzleQueue.isEmpty()) {
	  		PuzzleGrid<T> polledPuzzle = puzzleQueue.poll();
	  		if(polledPuzzle.isSolved()) {
	  			//done
  				PuzzleGrid<T> searchPuzzlePredecessor = polledPuzzle;
  				//make history 
  				while(history.containsKey(searchPuzzlePredecessor)) {
  					searchPuzzlePredecessor = history.get(searchPuzzlePredecessor);
  					System.out.println(searchPuzzlePredecessor);
  				}
  				return null;
  			}
	  		
	  		List<PuzzleGrid<T>> adjacentsPuzzle = polledPuzzle.getAdjacentPuzzles();	  		
	  		for(PuzzleGrid<T> adjPuzzle : adjacentsPuzzle) {	  			
	  			if(!history.containsKey(adjPuzzle)) {
	  				history.put(adjPuzzle, polledPuzzle);
		  			puzzleQueue.add(adjPuzzle);
	  			}	  			
	  		}
	  		iterationsNumber++;
	  	}
		return null;
	}


	@Override
	public Move getNextMove() {
		return null;
	}

}