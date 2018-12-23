/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Db_access {

    private static final String DB_USER = "user";
    private static final String DB_PASSWD = "1991";
//    private static final String databaseName="mixalakis";

    private static final String DB_URL = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/"+Main_application.databaseName+"?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&allowPublicKeyRetrieval=true";

    private Connection connection = null;

    //connecting everytime to the database and returning a connection object when needed
    public Connection connect() {

        try {
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            //   System.out.println("Connection to database was successful!");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());
            System.exit(0);
        }
        return connection;
    }

    //disconnecting from the database
    public void disconnect(Connection connection) {
        try {
            connection.close();
            //       System.out.println("Database has been disconnected!");
        } catch (SQLException ex) {
            System.out.println("Problem with closing the database!");
            Logger.getLogger(Db_access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getDB_USER() {
        return DB_USER;
    }

    public static String getDB_PASSWD() {
        return DB_PASSWD;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

}
