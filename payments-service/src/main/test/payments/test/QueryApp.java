package payments.test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class QueryApp {

	public static void main(String args[]) {
		QueryApp app = new QueryApp();
		app.getDbResults();
	}

	private void getDbResults() {

		try {

			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("paymentsdb");
			
			// get a single collection
			DBCollection collection = db.getCollection("paymentsdb");

			System.out.println("Find all matched documents");
			DBCursor cursor = collection.find();
			while (cursor.hasNext()) {
				System.out.println("Prining dcoument");
				System.out.println(cursor.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
