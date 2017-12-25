package ai;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class GUI extends JFrame implements Runnable {

	Thread thread;
	JMenuBar menuBar;
	JPanel panel;

	GUI() {

	}

	@Override
	public void run() {
		setTitle("¸õ´Ñ");
		setSize(1200, 900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initMenuBar();
		initPanel();

		setVisible(true);

		thread = new Thread(this);
		thread.start();
	}

	private void initMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	}
	
	private void initPanel() {
		panel = new JPanel();
		getContentPane().add(panel);
	}
}
