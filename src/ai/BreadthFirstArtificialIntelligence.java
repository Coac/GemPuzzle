package ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import element.PuzzleGrid;
import element.Tile;
import game.History;
import game.Move;

public class BreadthFirstArtificialIntelligence<T> extends AbstractArtificialIntelligence<T>{

	@Override
	public History solve() {
		History history = new History();
		LinkedList<PuzzleGrid<T>> queue = new LinkedList<PuzzleGrid<T>>();
		
		int iterNb=0;
		//insert init node to queue
		//insert init node to history sortedlist
		//queue empty ? sol not found : continue
		//pop a node N from queue
		//n == goal node ? sol found,  find path to the solution by following parent nodes of N : continue
		//get successors of N
		//successor in history ? continue :
		//insert successor to queue and history
		//successors finished ? goto queue empty : goto successors in history
		queue.add(this.grid);
		
		while(!queue.isEmpty()){
			PuzzleGrid<T> configuration = queue.poll();
			if(!this.grid.isSolved()){
				//get successors (configurations où on change la position du trou)
				//liste des successors
				LinkedList<Integer> successors = new LinkedList<>();
				
				
				for(int i=0; i < successors.size();i++);
				iterNb++;				
			}
			else{
				
			}
		}
		
		
		return null;
	}
	
	public List<PuzzleGrid<T>> aStarSolveMine(PuzzleGrid<T> puzzle) {
	  	HashMap<PuzzleGrid<T>,PuzzleGrid<T>> history = new HashMap<PuzzleGrid<T>,PuzzleGrid<T>>();
	  	Queue<PuzzleGrid<T>> puzzleQueue = new LinkedList<PuzzleGrid<T>>();
	  	
	  	puzzleQueue.add(puzzle);
	  	
	  	while(!puzzleQueue.isEmpty()) {
	  		
	  		PuzzleGrid<T> poppedPuzzle = puzzleQueue.poll();
	  		
	  		List<PuzzleGrid<T>> adjacentsPuzzle = poppedPuzzle.getAdjacentPuzzles();
	  		
	  		for(PuzzleGrid<T> adjPuzzle : adjacentsPuzzle) {
	  			
	  			if(!history.containsKey(adjPuzzle)) {
	  				history.put(adjPuzzle, poppedPuzzle);
		  			puzzleQueue.add(adjPuzzle);
	  			}
	  			if(adjPuzzle.isSolved()) {
	  				System.out.println("WINNNER");
	  				
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


	@Override
	public Move getNextMove() {
		return null;
	}

}
