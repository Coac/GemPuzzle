package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;

@SuppressWarnings("serial")
public class PanelGame extends JPanel {
	private static final int MARGIN_CASE = 5;

	private boolean editable = false;

	private PuzzleContext<Integer> puzzleContext;

	public PanelGame() {
		PuzzleGridsIntegerParser parser = new PuzzleGridsIntegerParser();
		try {
			puzzleContext = new PuzzleContext<Integer>(parser.parseFile(new File("test.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Action doNothing = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("a");
		    }
		};
		getInputMap().put(KeyStroke.getKeyStroke("F2"),
		                            "doNothing");
		getActionMap().put("doNothing",
		                             doNothing);
	}

	@Override
	public void paint(Graphics graph) {
		Graphics2D g = (Graphics2D) graph;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Background2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[] { 5 ,5}, 0
		g.setColor(new Color(150, 150, 150));
		g.fillRect(0, 0, getWidth(), getHeight());

		// Font
		int n = puzzleContext.getGrid().size();
		int sizeCase = Math.min(getWidth(), getHeight()) / n - MARGIN_CASE;
		Font font = new Font("Default", Font.BOLD, sizeCase / 2);
		FontMetrics metrics = g.getFontMetrics(font);
		g.setFont(font);

		// Cases
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				if (i + n * j == puzzleContext.getGrid().getNullIndex()) {
					g.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, new float[] { 5 ,5}, 0));
					g.setColor(new Color(100, 100, 100));
					g.drawRoundRect(2 * MARGIN_CASE + i * sizeCase, 2 * MARGIN_CASE + j * sizeCase,
							sizeCase - MARGIN_CASE, sizeCase - MARGIN_CASE, 16, 16);
				} else {
					g.setStroke(new BasicStroke(2));
					g.setColor(new Color(200, 200, 200));
					g.fillRoundRect(2 * MARGIN_CASE + i * sizeCase, 2 * MARGIN_CASE + j * sizeCase,
							sizeCase - MARGIN_CASE, sizeCase - MARGIN_CASE, 16, 16);

					g.setColor(new Color(100, 100, 100));
					g.drawRoundRect(2 * MARGIN_CASE + i * sizeCase, 2 * MARGIN_CASE + j * sizeCase,
							sizeCase - MARGIN_CASE, sizeCase - MARGIN_CASE, 16, 16);

					String str = puzzleContext.getGrid().getElement(i, j).toString();
					int fontX = (sizeCase - metrics.stringWidth(str)) / 2;
					int fontY = (sizeCase + metrics.getAscent() - metrics.getDescent()) / 2;
					g.drawString(str, 2 * MARGIN_CASE + i * sizeCase + fontX, 2 * MARGIN_CASE + j * sizeCase + fontY);
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
}
