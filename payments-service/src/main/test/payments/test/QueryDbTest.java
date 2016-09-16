package payments.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDbTest {

	public static void main(String args[]) {
		QueryDbTest test = new QueryDbTest(); 
		try{
			test.testDbConnection();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void testDbConnection() throws Exception {
		DriverManager.registerDriver(new org.h2.Driver());
		Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		Statement statement = connection.createStatement();
		ResultSet rs = null;
		try {
			rs = statement.executeQuery("SELECT * from payment_view");
			System.out.println("executed statement"+rs);
		} catch (SQLException sqle) {
			System.out.println("Table not found");
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}

		
	}

}
