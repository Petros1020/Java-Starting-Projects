/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapes;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User1;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author karag
 */
public class UserMapper implements RowMapper<User1> {
    @Override
    public User1 mapRow(ResultSet rs, int i) throws SQLException {
        User1 u = new User1();
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setFirstname(rs.getString("firstname"));
        u.setLastname(rs.getString("lastname"));
        u.setEmail(rs.getString("email"));
        return u;
    }
}
