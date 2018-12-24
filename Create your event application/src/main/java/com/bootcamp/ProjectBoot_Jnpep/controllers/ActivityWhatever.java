/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.controllers;

import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Controller;

/**
 *
 * @author pitpr
 */
@Controller
public class ActivityWhatever {

    @PersistenceContext
    private EntityManager entityManager;

    public void getChat(Integer activityId) {
        System.out.println(activityId);
        String sql = "SELECT activity FROM Activity activity WHERE activity.id = :id";
        System.out.println("ok");
        Query query =entityManager.createQuery(sql, Activity.class).setParameter("id", activityId);
        System.out.println("ok2");
        List<Activity> activityList = (List<Activity>) query.getResultList();
        System.out.println(activityList.get(0).getChatroom());
    }
}
