package com.telecome.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtils {
      private static String DRIVERCLASS="com.mysql.jdbc.Driver";
      private static String URL="jdbc:mysql:///telecom?useUnicode=true&characterEncoding=UTF-8";
      private static String USERNAME="root";
      private static String PASSWORD="liudi999";
      static{
    	  try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
      }
      public static Connection getConnection() throws SQLException{
    	 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
      }
      public static void close(Connection con,PreparedStatement pst,ResultSet rs) throws SQLException{
    	  if(rs!=null)
    		  rs.close();
    	  if(pst!=null)
    		  pst.close();
    	  if(con!=null)
    		  con.close();
      }
}  
