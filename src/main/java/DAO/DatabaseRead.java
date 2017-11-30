package DAO;

import Model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseRead {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://root.crxsl2fuaxgk.eu-central-1.rds.amazonaws.com:3306/TechnichDB";
    static Connection con;

    //Database credentials
    static final String USER = "root";
    static final String PASS = "Mysql*123";

    public List<User> getUser ()throws SQLException{
        try {
            //etabler forbindelse
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);

            // in the url we have to tell which account and password to use
            con =  DriverManager.getConnection(DATABASE_URL,USER,PASS);

            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT role, username, pw " +
                    "FROM users ;");

            List<User> list = new ArrayList<>();

            if (rs != null){
                while (rs.next()){
                    list.add(new User(rs.getString(1),rs.getString(2), rs.getString(3)));
                }
            }
            s.close();
            con.close();
            return list;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public List<Content> getAllContent ()throws SQLException{
        try {
            //etabler forbindelse
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);


            // in the url we have to tell which account and password to use
            con =  DriverManager.getConnection(DATABASE_URL,USER,PASS);

            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM TechnichDB.content; ;");

            List<Content> list = new ArrayList<>();

            if (rs != null){
                while (rs.next()){
                    list.add(new Content(rs.getInt(1),rs.getString(2)));
                }
            }
            s.close();
            con.close();
            return list;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

}