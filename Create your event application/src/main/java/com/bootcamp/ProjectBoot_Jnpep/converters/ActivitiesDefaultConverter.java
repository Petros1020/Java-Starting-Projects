/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.converters;

import com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityDefaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author karag
 */
@Component
public class ActivitiesDefaultConverter implements Converter<String, ActivitiesDefault> {

    @Autowired
    ActivityDefaultRepository activityDefaultDAO;

    @Override
    public ActivitiesDefault convert(String id) {
        return activityDefaultDAO.selectActDef(Integer.parseInt(id));
    }

}


