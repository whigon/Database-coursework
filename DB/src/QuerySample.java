import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class QuerySample extends JPanel implements ActionListener {
	// Create an instance of DatabaseConnection class
	private DatabaseConnection DC;
	// TODO Need to change
	private String[] requirement = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };
	// A panel for user to choose query requirement
	private JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
	// A textfield to show query result
	private JTextArea text = new JTextArea("");
	// Comfirm button
	private JButton button = new JButton("Query");
	// A comboBox including query requirement
	private JComboBox<String> comboBox = new JComboBox<String>();

	public QuerySample(DatabaseConnection dc) {
		// Set basic layout
		super(new GridLayout(1, 2, 0, 0));
		// Add database connection
		this.DC = dc;

		// TODO Need to change
		for (int i = 0; i < 15; i++) {
			comboBox.addItem(requirement[i]);
		}

		// Add comboBox to panel
		panel.add(comboBox);
		// Padding
		panel.add(new JPanel());
		// Add Query button to panel
		button.addActionListener(this);
		panel.add(button);

		// Set select panel size
		// panel.setSize(300, 500);
		// Set textfield size
		// text.setSize(300, 500);

		// Add select panel and textfield
		this.add(panel, BorderLayout.WEST);
		this.add(text, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int sel = comboBox.getSelectedIndex();
		DC.sampleQuery(sel);
		text.setText(DC.print());
	}

	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection();
		JFrame jf = new JFrame();
		QuerySample qs = new QuerySample(dc);
		// JButton jb = new JButton("123");
		// qs.add(jb);
		jf.getContentPane().add(qs);
		jf.setVisible(true);
		jf.setSize(600, 500);
	}
}
