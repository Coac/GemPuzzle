package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Move.MoveDirection;
import game.PuzzleContext;

@SuppressWarnings("serial")
public class PanelGame extends JPanel implements MoveListener {
	public static final int MARGIN_CASE = 5;
	public static final int MIN_SIZE_CASE = 42;

	private double animationProgress;

	private boolean editable;

	private Rectangle[] rectangleCases;
	private int selectedTile;

	private PuzzleContext<Integer> puzzleContext;

	private final WindowGemPuzzle windowGemPuzzle;

	public PanelGame(final WindowGemPuzzle windowGemPuzzle) {
		this.windowGemPuzzle = windowGemPuzzle;

		puzzleContext = null;

		rectangleCases = null;
		selectedTile = -1;

		animationProgress = 1;
		editable = false;

		// Keypressed listener
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						move(MoveDirection.Up);
						checkWin();
						break;
					case KeyEvent.VK_DOWN:
						move(MoveDirection.Down);
						checkWin();
						break;
					case KeyEvent.VK_LEFT:
						move(MoveDirection.Left);
						checkWin();
						break;
					case KeyEvent.VK_RIGHT:
						move(MoveDirection.Right);
						checkWin();
						break;
					default:
						break;
					}
				}

				return false;
			}
		});

		// Mouse listener
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (rectangleCases != null && selectedTile >= 0) {
					int newTile = -1;
					for (int i = 0; i < rectangleCases.length; i++) {
						if (rectangleCases[i].intersects(e.getX(), e.getY(), 1, 1)) {
							newTile = i;
						}
					}
					if (newTile >= 0) {
						puzzleContext.getGrid().swapIndex(selectedTile, newTile);
					}

					windowGemPuzzle.getPanelControl().setSolvable(puzzleContext.isSolvable());
				}
				selectedTile = -1;
				repaint();
			}

			public void mousePressed(MouseEvent e) {
				selectedTile = -1;
				if (editable && rectangleCases != null) {
					for (int i = 0; i < rectangleCases.length; i++) {
						if (rectangleCases[i].intersects(e.getX(), e.getY(), 1, 1)) {
							selectedTile = i;
						}
					}
				}
			}

			public void mouseClicked(MouseEvent e) {
				// TODO: move
				if (rectangleCases != null && puzzleContext != null) {
					for (int i = 0; i < rectangleCases.length; i++) {
						if (rectangleCases[i].intersects(e.getX(), e.getY(), 1, 1)) {
							if (i + 1 == puzzleContext.getGrid().getNullIndex()) {
								move(MoveDirection.Left);
								checkWin();
							} else if (i - 1 == puzzleContext.getGrid().getNullIndex()) {
								move(MoveDirection.Right);
								checkWin();
							} else if (i + puzzleContext.getGrid().size() == puzzleContext.getGrid().getNullIndex()) {
								move(MoveDirection.Up);
								checkWin();
							} else if (i - puzzleContext.getGrid().size() == puzzleContext.getGrid().getNullIndex()) {
								move(MoveDirection.Down);
								checkWin();
							}
						}
					}
				}
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				repaint();
			}
		});
	}

	public void checkWin() {
		if (puzzleContext != null && puzzleContext.isSolved()) {
			JOptionPane.showMessageDialog(windowGemPuzzle, "Bravo, vous avez gagné !", "Win",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public boolean move(MoveDirection moveDirection) {
		if (puzzleContext != null) {
			if (puzzleContext.move(moveDirection)) {
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

				return true;
			}
		}
		return false;
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
			g.drawString(str, x + fontX - MARGIN_CASE / 2, y + fontY - MARGIN_CASE / 2);
		}
	}

	@Override
	public void paint(Graphics graph) {
		Graphics2D g = (Graphics2D) graph;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, getWidth(), getHeight());

		if (puzzleContext != null) {
			int n = puzzleContext.getGrid().size();
			int sizeCase = Math.max(MIN_SIZE_CASE, Math.min(getWidth(), getHeight()) / n - MARGIN_CASE);

			// Rectangles
			rectangleCases = new Rectangle[n * n];

			// Tiles
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < n; i++) {
					double x = i * sizeCase;
					double y = j * sizeCase;
					if (i + n * j != selectedTile) {
						// Animation
						if (puzzleContext.getHistory().last() != null) {
							if (i + n * j != puzzleContext.getGrid().getNullIndex()) {
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
							} else {
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
						}
						y += (getHeight() - n * sizeCase - 2 * PanelGame.MARGIN_CASE) / 2;

						// Tile
						String str = null;
						if (i + n * j != puzzleContext.getGrid().getNullIndex()) {
							str = puzzleContext.getGrid().getElement(i, j).toString();
						}
						drawTile(g, str, MARGIN_CASE + (int) x, MARGIN_CASE + (int) y, sizeCase);
					}

					rectangleCases[i + n * j] = new Rectangle((int) x, (int) y, sizeCase, sizeCase);
				}
			}

			if (selectedTile >= 0) {
				Point mouse = getMousePosition();
				if (mouse != null) {
					int x = (int) (mouse.getX() - sizeCase / 2);
					int y = (int) (mouse.getY() - sizeCase / 2);

					String str = null;
					if (selectedTile != puzzleContext.getGrid().getNullIndex()) {
						str = puzzleContext.getGrid().getTile(selectedTile).getValue().toString();
					}
					drawTile(g, str, MARGIN_CASE + (int) x, MARGIN_CASE + (int) y, sizeCase);
				}
			}
		} else {
			rectangleCases = null;
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

	public void setPuzzleContext(PuzzleContext puzzleContext) {
		this.puzzleContext = puzzleContext;
		repaint();
		windowGemPuzzle.getPanelPreview().updatePreview();
	}
}
