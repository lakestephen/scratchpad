package com.concurrentperformance.h2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODO
 * User: Stephen
 */
public class H2Test {


	@Test
	public void h2Test() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.
				getConnection("jdbc:h2:~/test;AUTO_SERVER=TRUE", "sa", "");
		// add application code here

		PreparedStatement ps = conn.prepareStatement("insert into bag values (1123)");

		ps.execute();

		conn.close();
	}
}
