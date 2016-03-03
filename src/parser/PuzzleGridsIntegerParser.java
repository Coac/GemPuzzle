package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import element.PuzzleGrid;
import element.Tile;

public class PuzzleGridsIntegerParser implements AbstractPuzzleGridsParser<Integer> {

	public PuzzleGrid<Integer> parseFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int size = scanner.nextInt();

		List<Tile<Integer>> mixedTiles = new ArrayList<Tile<Integer>>();
		
		int nullIndex = 0;
		for (int i = 0; i < size * size; ++i) {
			int readedInteger = scanner.nextInt();
			if(readedInteger == 0)  {
				nullIndex = i;
			}
			mixedTiles.add(new Tile<Integer>(readedInteger, i));
		}

		for (int i = 0; i < size * size; ++i) {
			Tile<Integer> sortedTile = new Tile<Integer>(scanner.nextInt(), i);

			for (Tile<Integer> t : mixedTiles) {
				if (t.equals(sortedTile)) {
					t.setSortedPosition(i);
				}
			}
		}

		scanner.close();

		return new PuzzleGrid<Integer>(size, mixedTiles, nullIndex);
	}
}
