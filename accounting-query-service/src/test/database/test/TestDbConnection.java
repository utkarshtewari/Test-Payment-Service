package database.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDbConnection {

	public static void main(String[] a) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/accountsdb", "sa","");
		// add application code here
		System.out.println("got connection");
		
		Statement stmt =conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from accounts");
		
		System.out.println("got rs"+rs);
		
		stmt.close();
		
		conn.close();
	}

}
