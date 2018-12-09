import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Custom extends JPanel implements ActionListener {
	// Database connection
	DatabaseConnection DC;
	// A panel for user to input query sql
	private JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
	// A textfield to get query sql
	private JTextArea inputField = new JTextArea();
	// A textfield to show query result
	private JTextArea outputField = new JTextArea("");
	// Button
	JButton button = new JButton("Query");

	public Custom(DatabaseConnection dc) {
		// Set basic layout
		super(new GridLayout(1, 2, 0, 0));
		// Add database connection
		this.DC = dc;

		// Add input textfield
		inputField.setEditable(true);
		inputField.setLineWrap(true);
		panel.add(inputField);
		// Padding
		panel.add(new JLabel("Please input SQL statement", JLabel.CENTER));
		// Add button
		button.addActionListener(this);
		panel.add(button);

		// Add input textfield and output textfield
		this.add(panel);
		this.add(outputField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Input string
		String s;
		// Flag
		boolean b = false;

		try {
			// Get input string
			s = inputField.getText();
			// Query
			DC.query(s);
			// If success to query, set b to true
			b = true;
		} catch (SQLException e1) {
			// If fail to query, warning
			outputField.setText("Please enter correct SQL statement.");
			// Clear input text field
			inputField.setText("");
		}

		// If b is true
		if (b) {
			// Print result
			outputField.setText(DC.print());
			// Set input field null
			inputField.setText("");
		}
	}

	public static void main(String[] args) {

	}

}
