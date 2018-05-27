package server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * DBManager: Singleton pattern
 *
 *
 **/
public final class DBManager {
 
  private static DBManager _instance = null;
  private Connection _con = null;
 
  public DBManager() {
  
 	_con = getMySQLConnection();
		
  }
 
  //Thread safe instatiate method
  public static synchronized DBManager getInstance() {
    if (_instance == null) {
      _instance = new DBManager();
    }
    return _instance;
  }
 
  public Connection getConnection() {
    return _con;
  }
 
  /**
   * Connection to MySQL Database
   */
  private static Connection getMySQLConnection() {
    Connection con = null;
 
    try {
        Class.forName("com.mysql.jdbc.Driver");
      String strCon = "jdbc:mysql://localhost/province?user=root&password=12345";
      con = DriverManager.getConnection(strCon);
    } catch (SQLException se) {
      System.out.println(se);
    } catch (ClassNotFoundException ex) {
         System.out.println(ex);;
      }
    return con;
  }
  
}
