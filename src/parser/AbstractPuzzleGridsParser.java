package parser;

import java.io.File;
import java.io.FileNotFoundException;

import element.PuzzleGrid;

public interface AbstractPuzzleGridsParser<T> {
	public abstract PuzzleGrid<T> parseFile(File file) throws FileNotFoundException;
}
