package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault;
import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ActivityRepository {

    @PersistenceContext
    private EntityManager em;
    

    public List<Activity> selectActivities(ActivitiesDefault act) {

        String sql = "SELECT activity FROM Activity activity WHERE activity.type = :type";
        Query query = this.em.createQuery(sql, Activity.class).setParameter("type", act);
        List<Activity> activityList = (List<Activity>) query.getResultList();
        return activityList;
    }

    public List<Activity> selectActivitiesUpcoming(int userId) {

        String sql = "select a from Activity a, Participates p, User u\n"
                + "where p.userId = u.id and p.activityId = a.id and u.id=:userId and a.past=0";
        Query query = this.em.createQuery(sql, Activity.class).setParameter("userId", userId);
        List<Activity> activityList = (List<Activity>) query.getResultList();
        return activityList;
    }

    public List<Activity> selectActivitiesPast(int userId) {

        String sql = "select a from Activity a, Participates p, User u\n"
                + "where p.userId = u.id and p.activityId = a.id and u.id=:userId and a.past=1";
        Query query = this.em.createQuery(sql, Activity.class).setParameter("userId", userId);
        List<Activity> activityList = (List<Activity>) query.getResultList();
        return activityList;
    }

    public List<Activity> selectAjaxActivities(String type) {

        String sql = "SELECT distinct a \n"
                + "FROM Activity a, ActivitiesDefault ad\n"
                + "WHERE a.type=ad.id and ad.name LIKE :type\n"
                + "or a.name LIKE :type";
        Query query = this.em.createQuery(sql, Activity.class).setParameter("type", "%" + type + "%");
        List<Activity> activityList = (List<Activity>) query.getResultList();
        return activityList;
    }

    @Transactional
    public void insertActivity(Activity activity) {
        em.persist(activity);
    }

    public void getChat(Integer activityId) {
        System.out.println(activityId);
        String sql = "SELECT activity FROM Activity activity WHERE activity.id = :id";
        System.out.println("ok");
        Query query = this.em.createQuery(sql, Activity.class).setParameter("id", activityId);
        System.out.println("ok2");
        List<Activity> activityList = (List<Activity>) query.getResultList();
        System.out.println(activityList.get(0).getChatroom());
    }

}
