/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author karag
 */
@Repository
public class ActivityDefaultRepository {

    @PersistenceContext
    private EntityManager em;

    public ActivitiesDefault selectActDef(int id) {
        String sql = "SELECT act FROM ActivitiesDefault act WHERE act.id = :id";
        Query query = this.em.createQuery(sql, ActivitiesDefault.class).setParameter("id", id);
        List<ActivitiesDefault> actList = (List<ActivitiesDefault>) query.getResultList();
        return actList.get(0);
    }

    public List<ActivitiesDefault> selectAllActDef() {
        String sql = "SELECT act FROM ActivitiesDefault act";
        Query query = this.em.createQuery(sql, ActivitiesDefault.class);
        List<ActivitiesDefault> actList = (List<ActivitiesDefault>) query.getResultList();
        return actList;
    }

    public ActivitiesDefault selectActivitiesLike(String type) {
        String sql = "SELECT act FROM ActivitiesDefault act where name like :type";
        Query query = this.em.createQuery(sql, ActivitiesDefault.class).setParameter("type", "%"+type+"%");
        List<ActivitiesDefault> actList = (List<ActivitiesDefault>) query.getResultList();
        return actList.get(0);
    }

}
