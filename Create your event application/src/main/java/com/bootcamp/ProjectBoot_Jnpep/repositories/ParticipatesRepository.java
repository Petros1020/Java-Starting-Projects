/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.models.Participates;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pitpr
 */
@Repository
public interface ParticipatesRepository extends CrudRepository<Participates, Integer> {

    default boolean findByUserAndActivity(Activity activity, User user, EntityManager entityManager) {
        String sql = "SELECT * FROM participates WHERE activity_id='"+activity.getId()+"' AND user_id='"+user.getId()+"' ;";
//        List<User> userList = jdbcTemplate.query(sql, new UserMapper());
        List <Participates> participantsList = entityManager.createNativeQuery(sql, Participates.class).getResultList();
        return (!participantsList.isEmpty());

    }
    
     default Integer findIdByUserAndActivity(Activity activity, User user, EntityManager entityManager) {
        String sql = "SELECT * FROM participates WHERE activity_id='"+activity.getId()+"' AND user_id='"+user.getId()+"' ;";
//        List<User> userList = jdbcTemplate.query(sql, new UserMapper());
        List <Participates> participantsList = entityManager.createNativeQuery(sql, Participates.class).getResultList();
        if(!participantsList.isEmpty()){
        return (participantsList.get(0).getId());
        }else{
            return 0;
        }

    }
   
 long countByActivityId(Activity activity);
    

}
