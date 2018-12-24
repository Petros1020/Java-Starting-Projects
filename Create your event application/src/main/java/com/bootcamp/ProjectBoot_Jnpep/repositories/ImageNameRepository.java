/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.models.ImageName;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pitpr
 */
@Repository
public class ImageNameRepository {

    @PersistenceContext
    private EntityManager em;

    public String selectMaxImageName() {
     
        String sql = "SELECT imagename FROM ImageName imagename ORDER BY id DESC";
        Query query = this.em.createQuery(sql, ImageName.class);
        List<ImageName> nameList = (List<ImageName>) query.getResultList();
//        int maxid = nameList.get(0).getId();
//        ImageName img = new ImageName(maxid);
//        newImageName(img);
        System.out.println(nameList.get(0).getId().toString());
        return nameList.get(0).getId().toString();
    }
    
    @Transactional
    public void newImageName(ImageName img){
        em.persist(img);
    }
}
