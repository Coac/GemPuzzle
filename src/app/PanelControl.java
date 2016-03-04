package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

import ai.AStarMisplacedTilesArtificialIntelligence;
import ai.AbstractAStarArtificialIntelligence;
import ai.AbstractArtificialIntelligence;
import ai.BFSArtificialIntelligence;
import ai.DFSArtificialIntelligence;
import element.PuzzleGrid;
import element.Tile;
import game.History;
import game.PuzzleContext;
import parser.PuzzleGridsIntegerParser;

@SuppressWarnings("serial")
public class PanelControl extends JPanel implements ActionListener {

	private JButton buttonNew;
	private JButton buttonOpen;
	private JButton buttonSave;

	private JButton buttonSolve;
	private JButton buttonNext;

	private JComboBox listAI;
	private JLabel labelSolvable;

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
				new AbstractArtificialIntelligence[] { new AStarMisplacedTilesArtificialIntelligence<>(),
						new BFSArtificialIntelligence<>(), new DFSArtificialIntelligence<>() });

		add(listAI);

		buttonNext = new JButton("Aide");
		buttonNext.addActionListener(this);
		add(buttonNext);
		buttonSolve = new JButton("Résoudre");
		buttonSolve.addActionListener(this);
		add(buttonSolve);

		labelSolvable = new JLabel();
		labelSolvable.setForeground(Color.RED);
		add(labelSolvable);
	}

	public void setSolvable(boolean solvable) {
		if (solvable) {
			labelSolvable.setText("");
		} else {
			labelSolvable.setText("Attention: grille impossible");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonNew)) {
			// New
			Object[] possibilities = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			Object nb = JOptionPane.showInputDialog(windowGemPuzzle, "Longueur du puzzle", "Longueur",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { 2, 3, 4, 5, 6, 7, 8, 9 }, 3);
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

				// New grid
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
			PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
			if (puzzleContext != null) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(windowGemPuzzle) == JFileChooser.APPROVE_OPTION) {
					try {
						PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile());
						int n = puzzleContext.getGrid().size();
						writer.println(n + "");
						for (int j = 0; j < n; j++) {
							for (int i = 0; i < n; i++) {
								writer.write(puzzleContext.getGrid().getTile(i, j).getValue().toString() + " ");
							}
							writer.println("");
						}
						// Final Grid
						String[][] sortedPuzzle = new String[n][n];
						for (int i = 0; i < n * n; i++) {
							int pos = puzzleContext.getGrid().getTile(i).getSortedPosition();
							if (i == puzzleContext.getGrid().getNullIndex()) {
								sortedPuzzle[pos % n][pos / n] = "0";
							} else {
								sortedPuzzle[pos % n][pos / n] = puzzleContext.getGrid().getTile(i).getValue()
										.toString();
							}
						}
						for (int j = 0; j < n; j++) {
							for (int i = 0; i < n; i++) {
								writer.write(sortedPuzzle[i][j] + " ");
							}
							writer.println("");
						}
						writer.close();
					} catch (FileNotFoundException e1) {
						// e1.printStackTrace();
						JOptionPane.showMessageDialog(windowGemPuzzle, "Impossible d'écrire dans le fichier", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		} else if (e.getSource().equals(buttonNext)) {
			// Next move
			PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
			if (puzzleContext != null) {
				AbstractArtificialIntelligence ai = (AbstractArtificialIntelligence) listAI.getSelectedItem();
				puzzleContext.setAI(ai);
				ai.setGrid(puzzleContext.getGrid());
				windowGemPuzzle.getPanelGame().move(ai.getNextMove().get());
			}

		} else if (e.getSource().equals(buttonSolve)) {
			// Solve
			PuzzleContext puzzleContext = windowGemPuzzle.getPanelGame().getPuzzleContext();
			if (puzzleContext != null) {
				final AbstractArtificialIntelligence ai = (AbstractArtificialIntelligence) listAI.getSelectedItem();
				puzzleContext.setAI(ai);
				ai.setGrid(puzzleContext.getGrid());
				long time = System.nanoTime();
				final History history = ai.solve();
				final double elapsedTime = (System.nanoTime() - time);
				new Thread(new Runnable() {
					public void run() {
						for (int i = 0; i < history.size(); i++) {
							windowGemPuzzle.getPanelGame().move(history.get(i).get());
							try {
								Thread.sleep(2000 / history.size());
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						String stat = "Temps CPU: " + (elapsedTime / 1e9) + "s\nNombre d'itérations: "
								+ ai.getIterationsNumber() + "\n\n";
						for (int i = 0; i < ai.getStatistics().size(); i++) {
							stat += ai.getStatistics().get(i) + "\n";
						}
						JOptionPane.showMessageDialog(windowGemPuzzle, stat, "Statistiques", JOptionPane.PLAIN_MESSAGE);
					}
				}).start();
			}
		}

		windowGemPuzzle.getPanelHistory().updateHistory();
	}
}
