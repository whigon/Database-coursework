import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class is used for sample query
 * 
 * @author Yuexiang Li
 * @since 10.0
 */
public class QuerySample extends JPanel implements ActionListener {
	// Create an instance of DatabaseConnection class
	private DatabaseConnection DC;
	// Select requirement
	private String[] requirement = { "",
			"Present a report listing the Manager¡¯s name and telephone number for each hall of residence",
			"Present a report listing the names and student numbers of students with the details of their lease agreements",
			"Display the details of lease agreements that include the Summer Semester",
			"Display the details of the total rent paid by a given student",
			"Present a report on students that have not paid their invoices by a given date",
			"Display the details of flat inspections where the property was found to be in an unsatisfactory condition",
			"Present a report of the names and student numbers of students with their room number and place number in a particular hall of residence",
			"Present a report listing the details of all students currently on the waiting list for accommodation, that is, not placed",
			"Display the total number of students in each student category",
			"Present a report of the names and student numbers for all students who have not supplied details of their next-of-kin",
			"Display the name and internal telephone number of the Advisor of Studies for a particular student",
			"Display the minimum, maximum, and average monthly rent for rooms in halls of residence",
			"Display the total number of places in each hall of residence",
			"Display the staff number, name, age, and current location of all members of the accommodation staff who are over 60 years old today" };
	// A panel for user to choose query requirement
	private JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
	// A textfield to show query result
	private JTextArea text = new JTextArea("");
	// Add text area to a scroll pane
	private JScrollPane jsp = new JScrollPane(text);
	// Comfirm button
	private JButton button = new JButton("Query");
	// A comboBox including query requirement
	private JComboBox<String> comboBox = new JComboBox<String>();

	/**
	 * The constructor
	 * 
	 * @param dc
	 *            database connection
	 */
	public QuerySample(DatabaseConnection dc) {
		// Set basic layout
		super(new GridLayout(1, 2, 0, 0));
		// Add database connection
		this.DC = dc;

		// 15 selections
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
		this.add(jsp, BorderLayout.EAST);
	}

	/**
	 * Perform query
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int sel = comboBox.getSelectedIndex();
		DC.sampleQuery(sel);
		text.setText(DC.print());
	}

	public static void main(String[] args) {
		// DatabaseConnection dc = new DatabaseConnection();
		// JFrame jf = new JFrame();
		// QuerySample qs = new QuerySample(dc);
		// JButton jb = new JButton("123");
		// qs.add(jb);
		// jf.getContentPane().add(qs);
		// jf.setVisible(true);
		// jf.setSize(1000, 600);
	}
}
