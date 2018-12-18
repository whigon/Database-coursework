import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is used to show GUI
 * 
 * @author Yuexiang Li
 * @since 10.0
 */
public class GUI extends JFrame implements ActionListener {
	// Database connection
	DatabaseConnection DC = new DatabaseConnection();
	// Basic panel for initial interface
	private JPanel basicPanel = new JPanel(new GridLayout(3, 1));
	// Panel for buttons
	private JPanel panel = new JPanel(new GridLayout(1, 5, 0, 0));
	// Textfield
	private JLabel text = new JLabel("Welcome to university accommodation office database system", JLabel.CENTER);
	// Buttons
	private JButton button1 = new JButton("Sample Query");
	private JButton button2 = new JButton("Custom");

	/**
	 * The constructor
	 */
	public GUI() {
		// Initialize interface
		initializeInterface();

		// Setup
		this.setTitle("University Accommodation Office Database System.");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setVisible(true);
	}

	/**
	 * Initializer interface
	 */
	public void initializeInterface() {
		// Add textfield
		basicPanel.add(text);

		// Add two buttons
		panel.add(new JLabel());
		button1.addActionListener(this);
		panel.add(button1);
		panel.add(new JLabel());
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(new JLabel());
		basicPanel.add(panel);
		this.getContentPane().add(basicPanel);
	}

	/**
	 * Identify the event source and perform relevant action.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();

		// Change interface
		if (eventSource.equals(button1)) {
			// Remove the compoent on contentPane
			// this.removeAll(); this will remove JFrame's Pane
			this.getContentPane().removeAll();
			// Switch to sample switch
			this.getContentPane().add(new QuerySample(DC));
			// this.repaint();
			// This statement is required
			this.validate();
		} else {
			// Remove the compoent on contentPane
			this.getContentPane().removeAll();
			// Switch to custom panel
			this.getContentPane().add(new Custom(DC));
			this.validate();
		}
	}
}
