package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

import game.Move.MoveDirection;
import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;

@SuppressWarnings("serial")
public class PanelGame extends JPanel implements MoveListener {
	public static final int MARGIN_CASE = 5;
	public static final int MIN_SIZE_CASE = 42;

	private boolean editable = false;

	private PuzzleContext<Integer> puzzleContext;

	private double animationProgress = 1;

	private WindowGemPuzzle windowGemPuzzle;

	public PanelGame(WindowGemPuzzle windowGemPuzzle) {
		this.windowGemPuzzle = windowGemPuzzle;

		PuzzleGridsIntegerParser parser = new PuzzleGridsIntegerParser();
		try {
			puzzleContext = new PuzzleContext<Integer>(parser.parseFile(new File("assets/test.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Keypressed listener
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						move(MoveDirection.Up);
						break;
					case KeyEvent.VK_DOWN:
						move(MoveDirection.Down);
						break;
					case KeyEvent.VK_LEFT:
						move(MoveDirection.Left);
						break;
					case KeyEvent.VK_RIGHT:
						move(MoveDirection.Right);
						break;
					default:
						break;
					}
				}
				return false;
			}
		});
	}

	public void move(MoveDirection moveDirection) {
		if (puzzleContext.move(moveDirection)) {
			// TODO: show final state

			windowGemPuzzle.getPanelHistory().updateHistory();

			// Animation
			new Thread(new Runnable() {
				public void run() {
					animationProgress = 0;
					while (animationProgress < 1) {
						animationProgress += 0.1;
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					animationProgress = 1;
					repaint();
				}
			}).start();
		}
	}

	public void drawTile(Graphics2D g, String str, int x, int y, int size) {
		if (str == null) {
			// Null element
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[] { 5, 5 }, 0));
			g.setColor(new Color(100, 100, 100));

			g.drawRoundRect(x, y, size - MARGIN_CASE, size - MARGIN_CASE, 8, 8);
			
		} else {
			// Tile
			Font font = new Font("Default", Font.BOLD, size / 3);
			FontMetrics metrics = g.getFontMetrics(font);
			g.setFont(font);

			g.setStroke(new BasicStroke(2));
			g.setColor(new Color(200, 200, 200));

			g.fillRoundRect(x, y, size - MARGIN_CASE, size - MARGIN_CASE, 8, 8);

			g.setColor(new Color(100, 100, 100));
			g.drawRoundRect(x, y, size - MARGIN_CASE, size - MARGIN_CASE, 8, 8);

			int fontX = (size - metrics.stringWidth(str)) / 2;
			int fontY = (size + metrics.getAscent() - metrics.getDescent()) / 2;
			g.drawString(str, x + fontX, y + fontY);
		}
	}

	@Override
	public void paint(Graphics graph) {
		Graphics2D g = (Graphics2D) graph;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, getWidth(), getHeight());

		// Font
		int n = puzzleContext.getGrid().size();
		int sizeCase = Math.max(MIN_SIZE_CASE, Math.min(getWidth(), getHeight()) / n - MARGIN_CASE);
		Font font = new Font("Default", Font.BOLD, sizeCase / 3);
		FontMetrics metrics = g.getFontMetrics(font);
		g.setFont(font);

		// Tiles
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				if (i + n * j != puzzleContext.getGrid().getNullIndex()) {
					// Animation
					double x = i * sizeCase;
					double y = j * sizeCase;
					if (puzzleContext.getHistory().last() != null) {
						if (puzzleContext.getHistory().last().equals(MoveDirection.Left)
								&& (i - 1) + n * j == puzzleContext.getGrid().getNullIndex()) {
							x = (i - (1 - animationProgress)) * sizeCase;
						} else if (puzzleContext.getHistory().last().equals(MoveDirection.Right)
								&& (i + 1) + n * j == puzzleContext.getGrid().getNullIndex()) {
							x = (i + (1 - animationProgress)) * sizeCase;
						}
						if (puzzleContext.getHistory().last().equals(MoveDirection.Up)
								&& i + n * (j - 1) == puzzleContext.getGrid().getNullIndex()) {
							y = (j - (1 - animationProgress)) * sizeCase;
						} else if (puzzleContext.getHistory().last().equals(MoveDirection.Down)
								&& i + n * (j + 1) == puzzleContext.getGrid().getNullIndex()) {
							y = (j + (1 - animationProgress)) * sizeCase;
						}
					}
					y += (getHeight() - n * sizeCase - 2 * PanelGame.MARGIN_CASE) / 2;

					// Tile
					String str = puzzleContext.getGrid().getElement(i, j).toString();
					drawTile(g, str, MARGIN_CASE + (int) x, MARGIN_CASE + (int) y, sizeCase);
				} else {
					// Animation
					double x = i * sizeCase;
					double y = j * sizeCase;
					if (puzzleContext.getHistory().last() != null) {
						if (puzzleContext.getHistory().last().equals(MoveDirection.Left)) {
							x = (i + (1 - animationProgress)) * sizeCase;
						} else if (puzzleContext.getHistory().last().equals(MoveDirection.Right)) {
							x = (i - (1 - animationProgress)) * sizeCase;
						}

						if (puzzleContext.getHistory().last().equals(MoveDirection.Up)) {
							y = (j + (1 - animationProgress)) * sizeCase;
						} else if (puzzleContext.getHistory().last().equals(MoveDirection.Down)) {
							y = (j - (1 - animationProgress)) * sizeCase;
						}
					}
					y += (getHeight() - n * sizeCase - 2 * PanelGame.MARGIN_CASE) / 2;

					// Null element
					drawTile(g, null, MARGIN_CASE + (int) x, MARGIN_CASE + (int) y, sizeCase);
				}
			}
		}
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isEditable() {
		return editable;
	}

	public PuzzleContext<Integer> getPuzzleContext() {
		return puzzleContext;
	}
}
