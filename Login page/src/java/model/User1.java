/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karag
 */
@Entity
@Table(name = "user1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User1.findAll", query = "SELECT u FROM User1 u")
    , @NamedQuery(name = "User1.findByUsername", query = "SELECT u FROM User1 u WHERE u.username = :username")
    , @NamedQuery(name = "User1.findByPassword", query = "SELECT u FROM User1 u WHERE u.password = :password")
    , @NamedQuery(name = "User1.findByFirstname", query = "SELECT u FROM User1 u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "User1.findByLastname", query = "SELECT u FROM User1 u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User1.findByEmail", query = "SELECT u FROM User1 u WHERE u.email = :email")})
public class User1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;

    public User1() {
    }

    public User1(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User1)) {
            return false;
        }
        User1 other = (User1) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User1[ username=" + username + " ]";
    }
    
}
