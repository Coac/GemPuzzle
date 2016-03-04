import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import ai.AbstractAStarArtificialIntelligence;
import ai.AStarMisplacedTilesArtificialIntelligence;
import element.PuzzleGrid;
import game.Move;
import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;
import utils.Pair;

public class test2 {
	public static void main(String[] args) {
		PuzzleContext<Integer> puzzleContext = null;
		PuzzleGridsIntegerParser parser = new PuzzleGridsIntegerParser();
		try {
			puzzleContext = new PuzzleContext<Integer>(parser.parseFile(new File("assets/test.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println(puzzleContext.toString());

		System.out.println(puzzleContext.isSolved());
				
		
		AbstractAStarArtificialIntelligence<Integer> ai = new AStarMisplacedTilesArtificialIntelligence<Integer>();
		
		ai.setGrid(puzzleContext.getGrid());
		//System.out.println(puzzleContext.isSolvable());
		
		long time = System.nanoTime();
	    System.out.println(ai.solve().toString());
		
		//aStarSolve(puzzleContext.getGrid());
		System.out.println((System.nanoTime() - time) / 1000000 + " ms");
	}
	


}
