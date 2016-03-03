package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ai.ASTARArtificialIntelligence;
import ai.AbstractArtificialIntelligence;
import ai.BFSArtificialIntelligence;
import ai.DFSArtificialIntelligence;
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

			}

		} else if (e.getSource().equals(buttonOpen)) {
			// Open
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(windowGemPuzzle) == JFileChooser.APPROVE_OPTION) {
				PuzzleGridsIntegerParser parser = new PuzzleGridsIntegerParser();
				try {
					windowGemPuzzle.getPanelGame().setPuzzleContext(
							new PuzzleContext<Integer>(parser.parseFile(new File("assets/test.txt"))));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
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
