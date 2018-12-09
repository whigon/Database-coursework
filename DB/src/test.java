import java.sql.*;
import java.util.ArrayList;

public class test {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/DreamHome?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT fname, lname, position, salary FROM staff where salary between 8000 and 20000";
			ResultSet rs = stmt.executeQuery(sql);
			// System.out.println(rs);

			ResultSetMetaData a = rs.getMetaData();
			ArrayList<String> s = new ArrayList<>();
			int count = a.getColumnCount();
			for (int i = 1; i <= count; i++) {
				s.add(a.getColumnName(i));
				System.out.print(s.get(i - 1) + "\t");
			}

			System.out.println();

			// Extract data from result set
			while (rs.next()) {
				for (int i = 0; i < count; i++) {
					System.out.print(rs.getString(s.get(i)) + "\t");
				}
				System.out.println();
				
				// Retrieve by column name
				// ResultSetMetaData a = rs.getMetaData();
				// String fname = rs.getString("fname");
				// String lname = rs.getString("lname");
				// String position = rs.getString("position");
				// int salary = rs.getInt("salary");

				// Display values
				// System.out.print("fname: " + fname);
				// System.out.print(", lname: " + lname);
				// System.out.print(", position: " + position);
				// System.out.println(", salary: " + salary);
			}

			// Clean-up
			rs.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(conn);
	}

}
