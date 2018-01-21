package co.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Provider {
	static Connection con=null;
	
	public static Connection getConnection()
	{
		if(con==null)
		{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system","root","prakalpa");
			System.out.println("Connected with MySQL Server");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		return con;
	}

}
