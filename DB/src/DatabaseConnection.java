import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
	// Based on mysql-connector-java-8.0.13.jar
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// Database url
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/DreamHome?serverTimezone=UTC";
	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "123456";
	// Connection&Statement
	private Connection con = null;
	private Statement stm = null;
	// A set of result
	private ResultSet rs = null;
	// Print text.
	private String printText = "";
	// Store column name
	private ArrayList<String> colName = new ArrayList<>();
	// Meta data
	private ResultSetMetaData rsmd;

	/*
	 * 
	 */
	public DatabaseConnection() {
		// Connect to JDBC
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Fail to connect JDBC.");
		}

		System.out.println("Success to connect JDBC.");

		// Connect to database
		try {
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Fail to connect database.");
		}

		System.out.println("Success to connect database.");
	}

	/*
	 * 
	 */
	public void sampleQuery(int sel) {
		// Query statement
		String sql = "";

		// Select a sql statement
		switch (sel) {
		case 1:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 2:
			sql = "SELECT fname, lname, salary FROM staff where salary between 8000 and 20000";
			break;
		case 3:
			sql = "SELECT * FROM staff";
			break;
		case 4:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 5:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 6:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 7:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 8:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 9:
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			break;
		case 10:
			sql = "";
			break;
		case 11:
			sql = "";
			break;
		case 12:
			sql = "";
			break;
		case 13:
			sql = "";
			break;
		case 14:
			sql = "";
			break;
		default:
			sql = "";
			break;
		}

		// Query
		try {
			query(sql);
		} catch (SQLException e) {
			System.out.println("Fail to query.");
			printText = "Fail to query";
		}
	}

	/*
	 * 
	 */
	public void query(String sql) throws SQLException {
		// Clear statement
		stm = null;
		// Clear result set
		rs = null;

		// Execute query statement
		System.out.println("Creating statement...");
		// Create a statement
		stm = con.createStatement();
		// Execute statement
		rs = stm.executeQuery(sql);
		// System.out.println(rs);
		System.out.println("Success to query.");

		// Extract query result
		extractResult();
	}

	/*
	 * 
	 */
	public void extractResult() {
		// Clear printText
		printText = "";
		// Clear resultset meta data
		rsmd = null;
		// Clear arraylist
		colName.clear();
		// Column number
		int colCount;

		System.out.println("Extracting data from result set.");

		try {
			// Get meta data
			rsmd = rs.getMetaData();
			// Get total column count
			colCount = rsmd.getColumnCount();
			// Get column name
			for (int i = 1; i <= colCount; i++) {
				// Index in resultset begin from 1
				colName.add(rsmd.getColumnName(i));
				// Index in arraylist begin from 0
				printText += colName.get(i - 1) + "\t";
			}

			printText += "\n";
			// Get single result
			while (rs.next()) {
				for (int i = 0; i < colCount; i++) {
					printText += rs.getString(colName.get(i)) + "\t";
				}

				printText += "\n";
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Fail to extract data.");
		}
		System.out.println("Success to extract data.");
	}

	/*
	 * 
	 */
	public String print() {
		return "Result: \n" + printText;
	}

	public static void main(String[] args) {
		DatabaseConnection DC = new DatabaseConnection();
		DC.sampleQuery(2);
		// DC.extractResult(2);
		System.out.println(DC.print());
	}

}
