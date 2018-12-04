/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import mapes.UserMapper;
import model.User1;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


/**
 *
 * @author karag
 */

public class UserDAOimp implements UserDAO {

    private DataSource dataSource;

    public UserDAOimp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserDAOimp() {
    }

    /*
    selectUsers
    selects all users from the database
    @Returns - a list from all users selected
    */
    @Override
    public List<User1> selectUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "Select * from user1;";
        List<User1> listuser = jdbcTemplate.query(sql, new UserMapper());
        return listuser;
    }

    /*
    adduser
    adds a user into the database
    @Param u - a User1 entity to be added
    @Returns - the number of rows affected
    */
    @Override
    public int adduser(User1 u) {
        SimpleJdbcInsert dimpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("User1");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", u.getUsername());
        parameters.put("firstname", u.getFirstname());
        parameters.put("password", u.getPassword());
        parameters.put("lastname", u.getLastname());
        parameters.put("email", u.getEmail());
        return dimpleJdbcInsert.execute(parameters);
    }

    /*
    loginUser
    Checks if a user exists
    @Param username - the username to be checked
    @Return - a User1 object if username exists, otherwise null
    */
    @Override
    public User1 loginUser(String username) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "Select * from user1 where username=?";
        List<User1> users = jdbcTemplate.query(
                sql, new PreparedStatementSetter() {

            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
            }
        },
                new UserMapper());
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    /* 
    checkUsername
    Checks for the existence of a username in the database
    @Param username - the username to be checked
    @Return - true if user exists, false otherwise
    */
    @Override
    public boolean checkUsername(String username) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "Select * from user1 where username=?";
        List<User1> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {

            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, username);
            }
        },
                new UserMapper());
        if (users.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
