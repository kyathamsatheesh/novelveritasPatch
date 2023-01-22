package org.novelveritasPatch.rfc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String sql = "SELECT Posting_Key FROM bsegdata_2  LIMIT 10";
		String sql = "SELECT Posting_Key FROM bsegdata_2  LIMIT 10";
		String url = "jdbc:avatica:remote:url=http://192.168.0.9:8888/druid/v2/sql/avatica/";
        Properties connectionProperties = new Properties();
        try {
            try {
                Class.forName("org.apache.calcite.avatica.remote.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = DriverManager.getConnection(url, connectionProperties);
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "2015-09-12 00:00:00");
//            ps.setString(2, "2015-09-13 00:00:00");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getMetaData().getColumnName(1) + ":" + rs.getString(1));
//                System.out.println(rs.getMetaData().getColumnName(2) + ":" + rs.getInt(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
