package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import ai.AbstractArtificialIntelligence;
import game.History;
import game.Move;

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
				new AbstractArtificialIntelligence[] { new AbstractArtificialIntelligence() {
					public History solve() {
						return null;
					}

					public Move getNextMove() {
						return null;
					}

					public String toString() {
						return "AI 1";
					}
				}, new AbstractArtificialIntelligence() {
					public History solve() {
						return null;
					}

					public Move getNextMove() {
						return null;
					}

					public String toString() {
						return "AI 2";
					}
				} });
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

	}
}
