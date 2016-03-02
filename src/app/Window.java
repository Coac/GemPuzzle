package app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window() {
		super("GemPuzzle");

		// New, load, save

		setLayout(new BorderLayout());

		PanelGame panelGame = new PanelGame();
		add(panelGame, BorderLayout.CENTER);

		PanelControl panelControl = new PanelControl();
		add(panelControl, BorderLayout.WEST);

		setSize(800, 600);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
