package app;
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
			puzzleContext = new PuzzleContext<Integer>(
					parser.parseFile(new File("assets/sp007.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println(puzzleContext.toString());

		AbstractArtificialIntelligence<Integer> ai = new AStarManhattanArtificialIntelligence<Integer>();

		ai.setGrid(puzzleContext.getGrid());
		if (puzzleContext.isSolvable()) {
			System.out.println("isSolvable = TRUE");

			long time = System.nanoTime();
			System.out.println(ai.solve().toString());
			System.out.println((System.nanoTime() - time) / 1000000 + " ms");

		} else {
			System.out.println("isSolvable = FALSE");
		}

	}

}
