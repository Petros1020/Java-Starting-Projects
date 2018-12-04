/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import helper.BCrypt;
import java.io.IOException;
import java.util.List;
import model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author karag
 */
@Controller
public class SpringController {

    @Autowired
    private UserDAO userDAO;

    /*
    login
    @Param model - is not used
    @Param username - user's input from the username textbox
    @Param password - user's input from the password textbox
    @Return - forwards to success.jsp if the user exists, otherwise redirects to index.jsp
     */
    @RequestMapping(value = "/loginController.htm", method = RequestMethod.POST)
    public String login(ModelMap model, @RequestParam("u") String username, @RequestParam("p") String password) {
        User1 user = userDAO.loginUser(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return "success";
        } else {
            model.addAttribute("wrong", "Username or Password are incorrect! Please try again!");
            return "index";
        }
    }

    /*
    insertEmptyUser
    Creates an empty User1 to be populated in spring form
    @Param model - receives the empty user1
    @Returns - register.jsp
     */
    @RequestMapping(value = "/emptyuser.htm", method = RequestMethod.GET)
    public String insertEmptyUser(ModelMap model) {
        User1 u = new User1();
        model.addAttribute("user", u);
        return "register";
    }

    /*
    adduser
    Inserts a user1 into the database with hashed password.
    @Param u - user1 populated object delivered by the user
    @Param password1 - the verification of the password
    @Return - successReg.jsp if the both u.password and password are the same
    @Return - errorpage if the values are not the same
     */
    @RequestMapping(value = "/adduser.htm", method = RequestMethod.POST)
    public String adduser(ModelMap map, User1 u, String password1) {
        if (password1.equals(u.getPassword())) {
            String hashed = BCrypt.hashpw(password1, BCrypt.gensalt());
            u.setPassword(hashed);
        } else {
            return "erropage";
        }
        userDAO.adduser(u);
        List<User1> list = userDAO.selectUsers();
        map.addAttribute("ulist", list);
        return "successReg";
    }

    /*
    ajaxcheck
    checks if the username already exists
    @Param username - username typed bu the user
    @Return - message if the username exists or not
     */
    @RequestMapping(value = "/usernamecheck.htm", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
    public @ResponseBody
    String ajaxcheck(@RequestParam("username") String username) {
        boolean result = userDAO.checkUsername(username);
        String message;
        if (result) {
            message = "This user already exists!";
            return "{ \"message\":\"" + message + "\" }";
        } else {
            return "{ \"message\":\"This username is available\" }";
        }

    }

}
