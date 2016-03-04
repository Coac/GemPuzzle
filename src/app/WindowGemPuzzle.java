package app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WindowGemPuzzle extends JFrame {

	private PanelGame panelGame;
	private PanelControl panelControl;
	private PanelHistory panelHistory;
	private PanelPreview panelPreview;

	public WindowGemPuzzle() {
		super("GemPuzzle");

		setLayout(new BorderLayout());

		panelGame = new PanelGame(this);
		add(panelGame, BorderLayout.CENTER);

		panelControl = new PanelControl(this);
		add(panelControl, BorderLayout.NORTH);

		panelHistory = new PanelHistory(this);
		add(panelHistory, BorderLayout.SOUTH);

		panelPreview = new PanelPreview(this);
		add(panelPreview, BorderLayout.EAST);

		setSize(1000, 700);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public PanelGame getPanelGame() {
		return panelGame;
	}

	public PanelControl getPanelControl() {
		return panelControl;
	}

	public PanelHistory getPanelHistory() {
		return panelHistory;
	}

	public PanelPreview getPanelPreview() {
		return panelPreview;
	}
}
