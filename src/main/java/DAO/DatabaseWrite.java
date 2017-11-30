package DAO;

import Model.Content;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonemborg on 11/05/2017.
 */
public class DatabaseWrite {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://root.crxsl2fuaxgk.eu-central-1.rds.amazonaws.com:3306/TechnichDB";
    static Connection con;

    //Database credentials
    static final String USER = "root";
    static final String PASS = "Mysql*123";

    public void addContent (String post)throws SQLException {

        try {
            //etabler forbindelse
            con = null;
            Class.forName (JDBC_DRIVER);

            // in the url we have to tell which account and password to use
            con =  DriverManager.getConnection(DATABASE_URL,USER,PASS);

            PreparedStatement prpst;

            String sql = "insert into TechnichDB.content (content) VALUES (?)";
            prpst = con.prepareStatement(sql);
            prpst.setString(1, post);
            prpst.executeUpdate();

            con.close();


        } catch (Exception e){
        e.printStackTrace();
        }
    }
}
