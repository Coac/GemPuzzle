package app;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.PuzzleContext;

public class PanelPreview extends JPanel {
	private WindowGemPuzzle windowGemPuzzle;

	public PanelPreview(WindowGemPuzzle windowGemPuzzle) {
		this.windowGemPuzzle = windowGemPuzzle;
	}

	public void updatePreview() {
		PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
		if (puzzleContext == null) {
			Dimension dimension = new Dimension(0, 0);
			setPreferredSize(dimension);
			setSize(dimension);
		} else {
			Dimension dimension = new Dimension(
					2 * PanelGame.MARGIN_CASE + puzzleContext.getGrid().size() * PanelGame.MIN_SIZE_CASE, 0);
			setPreferredSize(dimension);
			setSize(dimension);
		}
		windowGemPuzzle.validate();
		repaint();
		windowGemPuzzle.validate();
	}

	@Override
	public void paint(Graphics graph) {
		PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();

		if (puzzleContext != null) {
			int n = puzzleContext.getGrid().size();

			Graphics2D g = (Graphics2D) graph;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Font font = new Font("Default", Font.BOLD, PanelGame.MIN_SIZE_CASE / 3);
			FontMetrics metrics = g.getFontMetrics(font);
			g.setFont(font);

			// Final Grid
			String[][] sortedPuzzle = new String[n][n];
			for (int i = 0; i < n * n; i++) {
				int pos = puzzleContext.getGrid().getTile(i).getSortedPosition();
				if (i == puzzleContext.getGrid().getNullIndex()) {
					sortedPuzzle[pos % n][pos / n] = null;
				} else {
					sortedPuzzle[pos % n][pos / n] = puzzleContext.getGrid().getTile(i).getValue().toString();
				}
			}

			// Tiles
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++) {
					int x = i * PanelGame.MIN_SIZE_CASE;
					int y = j * PanelGame.MIN_SIZE_CASE
							+ (getHeight() - n * PanelGame.MIN_SIZE_CASE - 2 * PanelGame.MARGIN_CASE) / 2;

					windowGemPuzzle.getPanelGame().drawTile(g, sortedPuzzle[i][j], PanelGame.MARGIN_CASE + x,
							PanelGame.MARGIN_CASE + y, PanelGame.MIN_SIZE_CASE);
				}
			}
		}
	}
}
