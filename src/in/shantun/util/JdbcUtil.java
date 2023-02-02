package in.shantun.util;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
	private JdbcUtil() {
		
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//using hikaricp mechanism it will provide logical connection.
	public static Connection getJdbcConnection() throws SQLException , IOException {
		HikariConfig config = new HikariConfig("src/in/shantun/properties/db.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		Connection logical_connection = dataSource.getConnection();
		return logical_connection;
	}
	
	//this will provide the physical connection.(old way)
	/*
	public static Connection getJdbcPhysicalCoonection() throws SQLException , IOException {
		FileInputStream fis = new FileInputStream("src/in/shantun/properties/db.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url , username , password);
		return connection;
	}
	*/

}
