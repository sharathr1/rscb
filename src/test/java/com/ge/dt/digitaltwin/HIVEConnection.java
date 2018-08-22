package com.ge.dt.digitaltwin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HIVEConnection {

	public static void main(String[] args) throws SQLException {
		System.out.println("test");
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Connection:" );

		Connection con = DriverManager.getConnection("jdbc:hive2://3.209.124.206:10000/digital_twin", "digital_twin",
				"xxx");
		Statement stmt = con.createStatement();
		System.out.println("Statement:" );

		String tableName = "region_expenditure";
	//	stmt.executeQuery("drop table " + tableName);
		//ResultSet res = stmt.executeQuery("create table " + tableName + " (id int, name string, dept string)");

		// show tables
		String sql = "show tables '" + tableName + "'";
		sql="desc digital_twin.system_component";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
			System.out.println(" :: "+res.getString(1));
		}
	}
}
