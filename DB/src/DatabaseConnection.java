import java.sql.*;
import java.util.ArrayList;

/**
 * This class is used to connect database
 * 
 * @author Yuexiang Li
 * @since 10.0
 */
public class DatabaseConnection {
	// Based on mysql-connector-java-8.0.13.jar
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// Database url
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/coursework?serverTimezone=UTC";
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

	/**
	 * The constructor
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

	/**
	 * This fuction is used to select a sql statement to query
	 * 
	 * @param sel
	 *            the index of sql statement
	 */
	public void sampleQuery(int sel) {
		// Query statement
		String sql = "";

		// Select a sql statement
		switch (sel) {
		case 1:
			sql = "SELECT \r\n" + 
					"    name, telephonenumber\r\n" + 
					"FROM\r\n" + 
					"    hallsofresidence;";
			break;
		case 2:
			sql = "SELECT \r\n" + 
					"    leases.stuno,\r\n" + 
					"    rentstudent.stuname,\r\n" + 
					"    leasesno,\r\n" + 
					"    duration,\r\n" + 
					"    placeno,\r\n" + 
					"    dateOfEnter,\r\n" + 
					"    dateOfLeave\r\n" + 
					"FROM\r\n" + 
					"    leases,\r\n" + 
					"    rentstudent\r\n" + 
					"WHERE\r\n" + 
					"    leases.stuNo = rentstudent.stuNo;";
			break;
		case 3:
			sql = "SELECT \r\n" + 
					"    Leases.stuno,\r\n" + 
					"    Leases.leasesno,\r\n" + 
					"    Leases.duration,\r\n" + 
					"    Leases.placeno,\r\n" + 
					"    Leases.dateOfEnter,\r\n" + 
					"    Leases.dateOfLeave,\r\n" + 
					"    Rooms.roomNo,\r\n" + 
					"    Address.city,\r\n" + 
					"    Address.street,\r\n" + 
					"    Address.postcode\r\n" + 
					"FROM\r\n" + 
					"    HallsOfResidence,\r\n" + 
					"    Leases,\r\n" + 
					"    rentroom,\r\n" + 
					"    Rooms,\r\n" + 
					"    Invoices,\r\n" + 
					"    Address\r\n" + 
					"WHERE\r\n" + 
					"    Rooms.nameOfResidence = HallsOfResidence.`name`\r\n" + 
					"        AND Leases.placeNo = rentroom.placeNo\r\n" + 
					"        AND rentroom.placeNo = Rooms.placeNo\r\n" + 
					"        AND Leases.leasesNo = Invoices.leasesNo\r\n" + 
					"        AND invoices.semester = 'summer'\r\n" + 
					"        AND Address.addressNo = HallsOfResidence.addressNo \r\n" + 
					"UNION SELECT \r\n" + 
					"    Leases.stuno,\r\n" + 
					"    Leases.Leasesno,\r\n" + 
					"    Leases.duration,\r\n" + 
					"    Leases.placeno,\r\n" + 
					"    Leases.dateOfEnter,\r\n" + 
					"    Leases.dateOfLeave,\r\n" + 
					"    Bedrooms.roomNo,\r\n" + 
					"    Address.city,\r\n" + 
					"    Address.street,\r\n" + 
					"    Address.postcode\r\n" + 
					"FROM\r\n" + 
					"    StudentFlats,\r\n" + 
					"    Bedrooms,\r\n" + 
					"    Leases,\r\n" + 
					"    rentroom,\r\n" + 
					"    Invoices,\r\n" + 
					"    Address\r\n" + 
					"WHERE\r\n" + 
					"    Leases.placeNo = rentroom.placeNo\r\n" + 
					"        AND rentroom.placeNo = Bedrooms.placeNo\r\n" + 
					"        AND Bedrooms.flatNo = StudentFlats.flatNo\r\n" + 
					"        AND Leases.leasesNo = Invoices.leasesNo\r\n" + 
					"        AND invoices.semester = 'summer'\r\n" + 
					"        AND Address.addressNo = StudentFlats.addressNo;";
			break;
		case 4:
			sql = "SELECT \r\n" + 
					"    *\r\n" + 
					"FROM\r\n" + 
					"    invoices\r\n" + 
					"WHERE\r\n" + 
					"    stuNo = '20160001';";
			break;
		case 5:
			sql = "SELECT \r\n" + 
					"    Student.stuNo,\r\n" + 
					"    fullName,\r\n" + 
					"    birthday,\r\n" + 
					"    sex,\r\n" + 
					"    category,\r\n" + 
					"    nationality,\r\n" + 
					"    smoker,\r\n" + 
					"    specialNeeds,\r\n" + 
					"    additionalComments,\r\n" + 
					"    currentStatus,\r\n" + 
					"    Address.city,\r\n" + 
					"    Address.street,\r\n" + 
					"    Address.postcode\r\n" + 
					"FROM\r\n" + 
					"    Student,\r\n" + 
					"    rentstudent,\r\n" + 
					"    Invoices,\r\n" + 
					"    Address\r\n" + 
					"WHERE\r\n" + 
					"    Student.stuNo = rentstudent.stuNo\r\n" + 
					"        AND rentstudent.stuNo = Invoices.stuNo\r\n" + 
					"        AND Student.addressNo = Address.addressNo\r\n" + 
					"        AND Invoices.paidMethod IS NULL;";
			break;
		case 6:
			sql = "SELECT \r\n" + 
					"    *\r\n" + 
					"FROM\r\n" + 
					"    flatinspection\r\n" + 
					"WHERE\r\n" + 
					"    satisfactoryCondition = 'no';";
			break;
		case 7:
			sql = "SELECT \r\n" + 
					"    rentstudent.stuNo, stuName\r\n" + 
					"FROM\r\n" + 
					"    rentstudent\r\n" + 
					"        JOIN\r\n" + 
					"    leases ON rentstudent.stuNo = leases.stuNo\r\n" + 
					"WHERE\r\n" + 
					"    leases.stuNo IN (SELECT \r\n" + 
					"            leases.stuNo\r\n" + 
					"        FROM\r\n" + 
					"            leases\r\n" + 
					"                JOIN\r\n" + 
					"            rentroom ON leases.placeNo = rentroom.placeNo\r\n" + 
					"        WHERE\r\n" + 
					"            rentroom.type = 'residence');";
			break;
		case 8:
			sql = "SELECT \r\n" + 
					"    *\r\n" + 
					"FROM\r\n" + 
					"    Student\r\n" + 
					"WHERE\r\n" + 
					"    Student.currentStatus = 'waiting';";
			break;
		case 9:
			sql = "SELECT \r\n" + 
					"    category, COUNT(*)\r\n" + 
					"FROM\r\n" + 
					"    Student\r\n" + 
					"GROUP BY (category);";
			break;
		case 10:
			sql = "SELECT \r\n" + 
					"    Student.stuNo,\r\n" + 
					"    Student.fullName,\r\n" + 
					"    StudentRelationship.`kin'sName`\r\n" + 
					"FROM\r\n" + 
					"    Student\r\n" + 
					"        LEFT JOIN\r\n" + 
					"    StudentRelationship ON Student.stuNo = StudentRelationship.studentNo\r\n" + 
					"WHERE\r\n" + 
					"    StudentRelationship.`kin'sName` IS NULL;";
			break;
		case 11:
			sql = "SELECT \r\n" + 
					"    Advisor.fullName, Advisor.telephoneNumber\r\n" + 
					"FROM\r\n" + 
					"    Advisor,\r\n" + 
					"    Student\r\n" + 
					"WHERE\r\n" + 
					"    Student.advisor = Advisor.fullName\r\n" + 
					"        AND Student.stuNo = '20160001';";
			break;
		case 12:
			sql = "SELECT \r\n" + 
					"    AVG(monthlyRentRate),\r\n" + 
					"    MAX(monthlyRentRate),\r\n" + 
					"    MIN(monthlyRentRate)\r\n" + 
					"FROM\r\n" + 
					"    Rooms;";
			break;
		case 13:
			sql = "SELECT \r\n" + 
					"    nameOfResidence, COUNT(*)\r\n" + 
					"FROM\r\n" + 
					"    Rooms\r\n" + 
					"GROUP BY nameOfResidence;";
			break;
		case 14:
			sql = "SELECT \r\n" + 
					"    *\r\n" + 
					"FROM\r\n" + 
					"    (SELECT \r\n" + 
					"        staffNo,\r\n" + 
					"            fullName,\r\n" + 
					"            DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW()) - TO_DAYS(`brithday`)), '%Y') + 0 AS age,\r\n" + 
					"            location\r\n" + 
					"    FROM\r\n" + 
					"        `AccommodationStaff`) AS t\r\n" + 
					"WHERE\r\n" + 
					"    t.age > 60;";
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

	/**
	 * Get the sql statement and query
	 * 
	 * @param sql
	 *            sql statement
	 * @throws SQLException
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

	/**
	 * Extract result to pintText from result set
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
				printText += colName.get(i - 1) + "\t\t";
			}

			printText += "\n";

			// Get single result
			while (rs.next()) {
				for (int i = 0; i < colCount; i++) {
					printText += rs.getString(colName.get(i)) + "\t\t";
				}

				printText += "\n";
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Fail to extract data.");
		}
		System.out.println("Success to extract data.");
	}

	/**
	 * Return the result string
	 * 
	 * @return result string
	 */
	public String print() {
		return "Result: \n" + printText;
	}
}
