package app;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ai.ASTARArtificialIntelligence;
import ai.AbstractArtificialIntelligence;
import ai.BFSArtificialIntelligence;
import ai.DFSArtificialIntelligence;
import element.PuzzleGrid;
import element.Tile;
import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;

@SuppressWarnings("serial")
public class PanelControl extends JPanel implements ActionListener {

	private JButton buttonNew;
	private JButton buttonOpen;
	private JButton buttonSave;

	private JButton buttonSolve;
	private JButton buttonNext;

	private WindowGemPuzzle windowGemPuzzle;

	public PanelControl(WindowGemPuzzle windowGemPuzzle) {
		this.windowGemPuzzle = windowGemPuzzle;

		JPanel panel1 = new JPanel();
		buttonNew = new JButton("Nouveau");
		buttonNew.addActionListener(this);
		panel1.add(buttonNew);
		buttonOpen = new JButton("Ouvrir");
		buttonOpen.addActionListener(this);
		panel1.add(buttonOpen);
		buttonSave = new JButton("Enregistrer");
		buttonSave.addActionListener(this);
		panel1.add(buttonSave);
		add(panel1);

		JComboBox<AbstractArtificialIntelligence> listAI = new JComboBox<>(
				new AbstractArtificialIntelligence[] { new ASTARArtificialIntelligence<>(),
						new BFSArtificialIntelligence<>(), new DFSArtificialIntelligence<>() });
		add(listAI);

		buttonNext = new JButton("Aide");
		buttonNext.addActionListener(this);
		add(buttonNext);
		buttonSolve = new JButton("Résoudre");
		buttonSolve.addActionListener(this);
		add(buttonSolve);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonNew)) {
			// New
			Object[] possibilities = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			Object nb = JOptionPane.showInputDialog(windowGemPuzzle, "Longueur du puzzle", "Longueur",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { 2, 3, 4, 5, 6, 7, 8, 9 }, 4);
			if (nb != null) {
				int n = Integer.parseInt(nb.toString());

				// Null element
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(n, n, PanelGame.MARGIN_CASE, PanelGame.MARGIN_CASE));
				final JToggleButton[] buttons = new JToggleButton[n * n];
				for (int i = 0; i < buttons.length; i++) {
					buttons[i] = new JToggleButton("");
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for (int i = 0; i < buttons.length; i++) {
								if (e.getSource().equals(buttons[i])) {
									buttons[i].setSelected(true);
								} else {
									buttons[i].setSelected(false);
								}
							}
						}
					});
					buttons[i].setPreferredSize(new Dimension(PanelGame.MIN_SIZE_CASE, PanelGame.MIN_SIZE_CASE));
					panel.add(buttons[i]);
				}
				buttons[0].setSelected(true);
				add(panel);

				JOptionPane.showMessageDialog(windowGemPuzzle,
						new JComponent[] { new JLabel("Selectionner l'élément de départ:"), panel },
						"Element de départ", JOptionPane.PLAIN_MESSAGE);

				int nullElement = 0;
				for (int i = 0; i < buttons.length; i++) {
					if (buttons[i].isSelected()) {
						nullElement = i;
					}
				}

				List<Tile<Integer>> mixedTiles = new ArrayList<Tile<Integer>>();
				int id = 1;
				for (int i = 0; i < n * n; i++) {
					if (i == nullElement) {
						mixedTiles.add(new Tile<Integer>(0, i));
					} else {
						mixedTiles.add(new Tile<Integer>(id, i));
						id++;
					}
				}
				PuzzleContext<Integer> puzzleContext = new PuzzleContext<Integer>(
						new PuzzleGrid<>(n, mixedTiles, nullElement));
				windowGemPuzzle.getPanelGame().setPuzzleContext(puzzleContext);
			}

		} else if (e.getSource().equals(buttonOpen)) {
			// Open
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(windowGemPuzzle) == JFileChooser.APPROVE_OPTION) {
				PuzzleGridsIntegerParser parser = new PuzzleGridsIntegerParser();
				try {
					windowGemPuzzle.getPanelGame().setPuzzleContext(
							new PuzzleContext<Integer>(parser.parseFile(fileChooser.getSelectedFile())));
				} catch (Exception e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(windowGemPuzzle, "Fichier invalide", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		} else if (e.getSource().equals(buttonSave)) {
			// Save
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(windowGemPuzzle) == JFileChooser.APPROVE_OPTION) {

			}

		}
	}
}
