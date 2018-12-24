package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.encryption.JBEncrypt;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> selectAllUsers() {

        String sql = "SELECT * FROM user";
        List<User> userList = (List<User>) this.em.createNativeQuery(sql, User.class).getResultList();
        return userList;
    }

    public User userLogin(User user) {

        String sql = "SELECT user FROM User user WHERE user.username=:username";
        Query query = this.em.createQuery(sql, User.class);
        query.setParameter("username", user.getUsername());
        List<User> userList = (List<User>) query.getResultList();
        if (!(userList.isEmpty())) {
            if (JBEncrypt.checkpw(user.getPassword(), userList.get(0).getPassword())) {
                user = userList.get(0);
                user.setPassword("");
                return user;
            }
        }

        return null;
    }

    public boolean userConfirm(User user) {

        String sql = "SELECT user FROM User user WHERE user.username=:username";
        Query query = this.em.createQuery(sql, User.class);
        query.setParameter("username", user.getUsername());
        List<User> userList = (List<User>) query.getResultList();
        if (!(userList.isEmpty())) {
            if (JBEncrypt.checkpw(user.getPassword(), userList.get(0).getPassword())) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public void usernameUpdate(User detachedUser) {
        User user = em.find(User.class, detachedUser.getId());
        user.setUsername(detachedUser.getUsername());
    }

    @Transactional
    public void emailUpdate(User detachedUser) {
        User user = em.find(User.class, detachedUser.getId());
        user.setEmail(detachedUser.getEmail());
    }

    @Transactional
    public void passwordUpdate(User detachedUser) {
        User user = em.find(User.class, detachedUser.getId());
        user.setPassword(detachedUser.getPassword());
    }

    @Transactional
    public void infoUpdate(User detachedUser) {
        User user = em.find(User.class, detachedUser.getId());
        user.setUsername(detachedUser.getUsername());
        user.setProfilePic(detachedUser.getProfilePic());
        user.setEmail(detachedUser.getEmail());

    }

    public List<User> selectUser(String searchWord) {

        String sql = "SELECT user FROM User user WHERE user.username LIKE :username";
        Query query = this.em.createQuery(sql, User.class).setParameter("username", "%" + searchWord + "%");
        List<User> userList = (List<User>) query.getResultList();
        return userList;
    }

    @Transactional
    public void deleteUser(Integer id) {
//        em.getTransaction().begin();
//        em.merge(member);
        User userToDelete = em.find(User.class, id);
        em.remove(userToDelete);
//        em.flush();
//        em.getTransaction().commit();
    }

    @Transactional
    public User findUser(Integer id) {
//        em.getTransaction().begin();
//        em.merge(member);
        User user = em.find(User.class, id);
//        em.flush();
//        em.getTransaction().commit();
        return user;
    }

    @Transactional
    public void insert(User user) {
//        em.getTransaction().begin();
        em.persist(user);
//        em.flush();
//        em.getTransaction().commit();
    }

    public List<User> selectUserParticipates(int actId) {

        String sql = "select u  from Activity a, Participates p, User u\n"
                + "where p.userId = u.id and p.activityId = a.id and a.id=:actId";
        Query query = this.em.createQuery(sql, User.class).setParameter("actId",actId);
        List<User> userList = (List<User>) query.getResultList();
        return userList;
    }
}
