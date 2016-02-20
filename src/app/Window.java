package app;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window() {
		super("GemPuzzle");
		
		setLayout(new BorderLayout());
		add(new PanelGame(), BorderLayout.CENTER);
		
		setSize(800, 600);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
