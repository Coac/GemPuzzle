package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import game.Move;
import game.PuzzleContext;

public class PanelHistory extends JPanel {
	private JList listHistory;
	private JLabel labelNbMoves;
	private JButton buttonClear;

	private WindowGemPuzzle windowGemPuzzle;

	public PanelHistory(final WindowGemPuzzle windowGemPuzzle) {
		super(new BorderLayout());

		this.windowGemPuzzle = windowGemPuzzle;

		listHistory = new JList();
		// listHistory.setFocusable(false);
		listHistory.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listHistory.setVisibleRowCount(1);
		JScrollPane pane = new JScrollPane(listHistory);
		pane.setPreferredSize(new Dimension(0, 42));

		add(pane, BorderLayout.CENTER);
		
		labelNbMoves = new JLabel();
		add(labelNbMoves, BorderLayout.EAST);
		
		buttonClear = new JButton("Vider l'historique");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
				if (puzzleContext != null) {
					puzzleContext.getHistory().clear();
				}
			}
		});
		add(buttonClear, BorderLayout.WEST);
	}

	public void updateHistory() {
		final PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
		if (puzzleContext != null) {
			listHistory.setModel(new AbstractListModel<Move>() {
				public int getSize() {
					return puzzleContext.getHistory().size();
				}

				public Move getElementAt(int i) {
					return puzzleContext.getHistory().get(getSize() - i - 1);
				}
			});
			
			labelNbMoves.setText("Nombre de déplacements: " + puzzleContext.getHistory().size());
		}else{
			labelNbMoves.setText("");
		}
	}
}
