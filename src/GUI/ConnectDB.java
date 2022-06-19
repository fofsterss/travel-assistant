package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.cj.xdevapi.Statement;

import java.util.logging.Level;

public class ConnectDB {
	
	static Connection connection = null;
	static String databaseName = "project_db";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;
	
	static String username = "root";
	static String password = "eora12mydudes";
	

	public static void main(String[] args) throws SQLException{
		ConnectDB project = new ConnectDB();
		project.createConnection();
	}

	void createConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
