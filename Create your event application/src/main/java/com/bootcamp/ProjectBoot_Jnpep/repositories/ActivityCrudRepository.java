/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories;

import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pitpr
 */
@Repository
public interface ActivityCrudRepository extends CrudRepository <Activity,Integer> {
    
}
