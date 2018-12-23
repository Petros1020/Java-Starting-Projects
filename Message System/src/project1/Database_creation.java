/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author karag
 */
public class Database_creation {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultset = null;
//    private final String rootuser = "root";
//    private final String rootpass = "1991";
    private final String DB_URL = "localhost:3306";

    public boolean databaseExists(String rootUsername,String rootPassword, String databaseName) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/?zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false", rootUsername, rootPassword);
            resultset = connection.getMetaData().getCatalogs();
            while (resultset.next()) {
                if (resultset.getString(1).equals(databaseName)) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //συναρτήσεις για να γίνει η database στον εκάστοτε υπολογιστή
    public void create_database(String rootUsername,String rootPassword, String databaseName) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false", rootUsername, rootPassword);
            statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE IF EXISTS `"+databaseName+"` ;");
            statement.executeUpdate("CREATE DATABASE `"+databaseName+"` ;");
            System.out.println("Database "+databaseName+" created!");
            //  connection.close();
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
        create_table_user(rootUsername,rootPassword, databaseName);
        create_table_data(rootUsername, rootPassword, databaseName);
        populate_user_table(rootUsername,rootPassword,databaseName);
    }

    public void create_table_user(String rootUsername,String rootPassword, String databaseName) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/?zeroDateTimeBehavior=CONVERT_TO_NULL", rootUsername, rootPassword);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE `"+databaseName+"`.`user` (\n"
                    + "  `username` VARCHAR(45) NOT NULL,\n"
                    + "  `password` VARCHAR(45) NOT NULL,\n"
                    + "  `jurisdiction` VARCHAR(45) NOT NULL,\n"
                    + "  PRIMARY KEY (`username`));");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void create_table_data(String rootUsername,String rootPassword, String databaseName) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/?zeroDateTimeBehavior=CONVERT_TO_NULL", rootUsername, rootPassword);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE `"+databaseName+"`.`data` (\n"
                    + "  `msg_id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `receiver` VARCHAR(45) NOT NULL,\n"
                    + "  `sender` VARCHAR(45) NOT NULL,\n"
                    + "  `date` VARCHAR(45) NOT NULL,\n"
                    + "  `message` VARCHAR(250) NOT NULL,\n"
                    + "  `seen` VARCHAR(45) NOT NULL DEFAULT 'No',\n"
                    + "  `edited_by` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`msg_id`),\n"
                    + "  INDEX `receiver_id_idx` (`receiver` ASC) VISIBLE,\n"
                    + "  INDEX `sender_id_idx` (`sender` ASC) VISIBLE,\n"
                    + "  CONSTRAINT `receiver_id`\n"
                    + "    FOREIGN KEY (`receiver`)\n"
                    + "    REFERENCES `"+databaseName+"`.`user` (`username`)\n"
                    + "    ON DELETE CASCADE\n"
                    + "    ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `sender_id`\n"
                    + "    FOREIGN KEY (`sender`)\n"
                    + "    REFERENCES `"+databaseName+"`.`user` (`username`)\n"
                    + "    ON DELETE CASCADE\n"
                    + "    ON UPDATE CASCADE);");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void populate_user_table(String rootUsername,String rootPassword, String databaseName) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/?zeroDateTimeBehavior=CONVERT_TO_NULL", rootUsername, rootPassword);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('admin', 'admin', 'super admin');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('thomas', '1234', 'C');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('arthur', '1234', 'A');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('john', '1234', 'A');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('polly', '1234', 'B');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('michael', '1234', 'user');");
            statement.executeUpdate("INSERT INTO `"+databaseName+"`.`user` (`username`, `password`, `jurisdiction`) VALUES ('finn', '1234', 'user');");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }

}
