/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 *
 * @author karag
 */
public class Database_manipulation {

    private Statement statement = null;
    private ResultSet resultSet = null;
    private final Db_access db = new Db_access();
    private PreparedStatement pst = null;
    private Connection connection = null;
    /////////// CHECKING PASSWORD //////////////
    public boolean check_userinfo(String username, String password) {

        boolean entry = false;
        try {
            connection = db.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT username FROM user WHERE password='" + password + "' and username='" + username + "';");
            //εάν το resultset γυρίσει κάτι τότε σημαίνει ότι το username και το password είναι σωστά
            if (resultSet.next()) {
                entry = true;
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
                
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }

        return entry;
    }

    /////////// RETURNING VALUE FUNCTIONS ////////////////
    public String oneInputInfo(String table_name, String column_name, String input, String returned_table_value) {
        // μέθοδος για να γυρίζει πίσω μια τιμή από το την database για οποιοδήποτε πίνακα
        // επιλέγει table (table_name), τη στήλη που θέλεις έλεγχο και την τιμή που θα έλεγχθέι (WHERE conlumn_name = input)
        // μετά, εφόσον με το * έχουμε επιστρέψει τα πάντα από τη συγκεκριμένη γραμμή, επιλέγει το όνομα της στήλεις που μας ενδιαφέρει (resultSet.getString(returned_table_value);)
        String information = null;

        try {
            connection = db.connect();
            String sql = "SELECT * FROM " + table_name + " WHERE " + column_name + "=?;";
            pst = connection.prepareStatement(sql);
            pst.setString(1, input);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                information = resultSet.getString(returned_table_value);
            }
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                pst.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        
        return information;
    }

    public void sendMsg(String receiver, String sender, String dateTime, String message) {
        //μέθοδος για να στείλουμε ένα μήνυμα. 

        Files_access.fileLogs(sender, receiver, dateTime, message);

        try {
            connection = db.connect();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `karagiannis_petros_project1`.`data` (`receiver`, `sender`, `date`, `message`) VALUES ('" + receiver + "', '" + sender + "', '" + dateTime + "', '" + message + "');");
            System.out.println("Message sent!");
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        

    }

    ////////// READING FUNCTIONS ////////////////
    public ArrayList readAllMessages(String receiverOrSender, String username, boolean changeSeen) {
        //μέθοδος για να διαβαστούν μηνύματα από κάποιον sender ή receiver (από  την προσωπική σελίδα του χρήστη). Η μέθοδος μπορεί όταν χρειάζεται να αποθηκεύει και τα msg_id
        ArrayList<String> list = new ArrayList<String>();
        try {
            connection = db.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM data WHERE " + receiverOrSender + "='" + username + "';");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
                list.add(resultSet.getString(1));
                if (resultSet.getString("seen").equals("No") && changeSeen == true) {
                    //όταν το μήνυμα διαβαστεί τότε μετατρέπεται το seen σε Yes
                    this.messageDiplayed(resultSet.getString("msg_id"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        return list;
    }

    public ArrayList readConversation(String username, String sender, boolean changeSeen) {
        //μέθοδος για να διαβαστεί μία συνομιλία μεταξύ 2 χρηστών από την προσωπική σελίδα του χρήστη
        ArrayList<String> list = new ArrayList<String>();
        try {
            connection = db.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM data WHERE receiver='" + username + "' and sender='" + sender + "' or receiver='" + sender + "' and sender='" + username + "';");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
                list.add(resultSet.getString(1));
                if (resultSet.getString("seen").equals("No") && changeSeen == true) {
                    //όταν το μήνυμα διαβαστεί τότε μετατρέπεται το seen σε Yes
                    this.messageDiplayed(resultSet.getString("msg_id"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                ex.getMessage();
                System.out.println("Problem with result or statement closing");
            }
        }
        return list;
    }

    public ArrayList readConversation(String username) {
        // διαβάζει όλα μηνύματα που έστειλε ένας sender στον χρήστη που είναι loged in
        ArrayList<String> list = new ArrayList<String>();
        try {
            connection = db.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM data WHERE sender='" + username + "';");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));
                list.add(resultSet.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        return list;
    }

    public ArrayList readTable(String table_name, String returned_value) {
        //διαβάζει τα πάντα από ένα table και γύρνάει array με όποιο value εισαχθεί
        ArrayList<String> list = new ArrayList<String>();
        try {
            connection = db.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table_name + ";");
            //εμφανίζει τα πάντα για οποιονδήποτε πίνακα, μιας και γνωρίζουμε τις στήλες κάθε πίνακα με τα metadata
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cols = rsmd.getColumnCount();
            if (table_name.equals("data")) {
                //αυτό το κάνω για να μη δείχνει τις στήλες seen,edited_by στο print
                cols = cols - 2;
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
                list.add(resultSet.getString(returned_value));
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        return list;
    }

    ////////// UPDATE FUNCTIONS /////////////
    public void updateMessage(String msg, String id, String username) {
        //κάνει edit ένα μύνημα και ανημερώνει τον πίνακας για το ποιος χρήστης το έκανε
        try {
            connection = db.connect();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE data SET message='" + msg + "', edited_by='" + username + "' WHERE msg_id=" + id + ";");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
    }

    //////////// DELETE FUNCTIONS /////////////
    public void deleteFromTable(String table_name, String where, String input) {
        //διαγράφει από συγκεκριμένο πίνακα συγκεκριμένη στήλη
        try {
            connection = db.connect();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + table_name + " WHERE " + where + "='" + input + "';");

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                resultSet.close();
                statement.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
    }

    ////////////////// Insert user //////////////////
    public void insertUser(String username, String password, String jurisdiction) {
        //εισάγει χρήστη στη βάση δεδομένων
        try {
            connection = db.connect();
            String sql = "INSERT INTO user (username,password,jurisdiction) VALUES(?,?,?);";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, jurisdiction);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                pst.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }

    }

    public void alterUser(String username, String alterValue, String newValue) {
        //κάνει edit κάποιον user
        try {
            connection = db.connect();
            String sql = "UPDATE user SET " + alterValue + "=? WHERE username=?;";
            pst = connection.prepareStatement(sql);
            pst.setString(1, newValue);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                pst.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
    }
///////////////// messages seen 

    public int howManyNotSeenMessages(String username) {
        // μετράει πόσα μυνήματα δεν έχουν διαβαστεί για κάθε χρήστη
        int notSeenMessages = 0;
        try {
            connection = db.connect();
            String sql = "select count(*) from data where seen='No' and receiver = ?;";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            resultSet = pst.executeQuery();
            resultSet.next();
            notSeenMessages = resultSet.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                pst.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        return notSeenMessages;
    }

    public void messageDiplayed(String msg_id) {
        //αλλάσει το seen από no σε yes
        try {
            connection = db.connect();
            String sql = "UPDATE data SET seen='Yes' WHERE msg_id=?;";
            pst = connection.prepareStatement(sql);
            pst.setString(1, msg_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                pst.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
    }

    public ArrayList<String> notSeenMessages(String username) {
        // δείχνει τα μυνήματα που έχουν στο seen = no
        ArrayList<String> list = new ArrayList();
        try {
            connection = db.connect();
            String sql = "SELECT * FROM data WHERE receiver = ? and seen = 'No';";
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("msg_id") + "\t" + resultSet.getString("sender") + "\t" + resultSet.getString("receiver") + "\t" + resultSet.getString("message"));
                this.messageDiplayed(resultSet.getString("msg_id"));
            }

        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection!");
            System.out.println(ex.toString());

        } finally {
            try {
                pst.close();
                resultSet.close();
                db.disconnect(connection);
            } catch (SQLException ex) {
                System.out.println("Problem with result or statement closing");
            }
        }
        return list;
    }

}
