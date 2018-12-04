/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.sql.DataSource;
import model.User1;


/**
 *
 * @author karag
 */
public interface UserDAO {
    public List<User1> selectUsers();
    
    public User1 loginUser(String username);
    
    public boolean checkUsername(String username);
    public int adduser(User1 u);
}
