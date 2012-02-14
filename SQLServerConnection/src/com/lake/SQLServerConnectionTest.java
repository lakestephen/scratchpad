package com.lake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class SQLServerConnectionTest {
	
	public static void main(String[] args) {
		String url = args[0];
		System.out.println("URL: " + url);
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

			Connection c = DriverManager.getConnection(url);
//        		"jdbc:jtds:sqlserver://SV144402.ztb.icb.commerzbank.com:1433/Sonaris14;domain=ad");
        
		System.out.println("got connection " + c);
		
		} catch (ClassNotFoundException e) {
			// SJL Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// SJL Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
