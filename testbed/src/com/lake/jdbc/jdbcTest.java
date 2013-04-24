package com.lake.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.junit.Test;

public class jdbcTest {

	
	@Test
	public void TestJDBC() {
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection c = DriverManager.getConnection("jdbc:db2://10.187.72.230:51000/CDB_SJL", "pcms_data_manager", "pcms");
			DatabaseMetaData md = c.getMetaData();

			System.out.println("getDatabaseProductName: " + md.getDatabaseProductName());
			System.out.println("getDatabaseProductVersion: " + md.getDatabaseProductVersion());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
