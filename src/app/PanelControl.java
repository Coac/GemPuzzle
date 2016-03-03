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

	private JButton bouttonNew;
	private JButton bouttonOpen;
	private JButton bouttonSave;

	private WindowGemPuzzle windowGemPuzzle;

	public PanelControl(WindowGemPuzzle windowGemPuzzle) {
		this.windowGemPuzzle = windowGemPuzzle;

		JPanel panel1 = new JPanel();
		bouttonNew = new JButton("Nouveau");
		bouttonNew.addActionListener(this);
		panel1.add(bouttonNew);
		bouttonOpen = new JButton("Ouvrir");
		bouttonOpen.addActionListener(this);
		panel1.add(bouttonOpen);
		bouttonSave = new JButton("Enregistrer");
		bouttonSave.addActionListener(this);
		panel1.add(bouttonSave);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
