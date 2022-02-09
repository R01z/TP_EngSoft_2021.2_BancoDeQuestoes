package com.bancodequestoes.config.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

  private static Connection conn = null;

//  public static Connection getConnection() {
//    if (conn == null) {
//      try {
//        URI dbUri = new URI(DATABASE_URL);
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl =
//            "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//        conn = DriverManager.getConnection(dbUrl, username, password);
//      } catch (SQLException | URISyntaxException e) {
//        throw new DbException(e.getMessage());
//      }
//    }
//    return conn;
//  }

  	public static Connection getConnection() {
  		if (conn == null) {
  			try {
  				Properties props = loadProperties();
          String url = props.getProperty("db.url");
        conn =
            DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/banco-de-questoes?user=admin&password=password");
  			}
  			catch (SQLException e) {
  				throw new DbException(e.getMessage());
  			}
  		}
  		return conn;
  	}

  public static void closeConnection() {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  private static Properties loadProperties() {
    try (InputStream fs = DB.class.getClassLoader().getResourceAsStream(("db.properties"))) {
      Properties props = new Properties();
      props.load(fs);
      return props;
    } catch (IOException e) {
      throw new DbException(e.getMessage());
    }
  }

  public static void closeStatement(Statement st) {
    if (st != null) {
      try {
        st.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  public static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }
}
