import java.io.File;
import java.io.FileNotFoundException;

import ai.AStarManhattanArtificialIntelligence;
import ai.AbstractArtificialIntelligence;
import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;

public class Test {
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

		AbstractArtificialIntelligence<Integer> ai = new AStarManhattanArtificialIntelligence<Integer>();

		ai.setGrid(puzzleContext.getGrid());
		// System.out.println(puzzleContext.isSolvable());

		long time = System.nanoTime();
		System.out.println(ai.solve().toString());

		// aStarSolve(puzzleContext.getGrid());
		System.out.println((System.nanoTime() - time) / 1000000 + " ms");
	}

}
