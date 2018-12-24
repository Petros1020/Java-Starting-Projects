/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories.tests;

import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ImageNameRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ParticipatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pitpr
 */
@Controller
public class ParticipatesRepositoryTest {
    
    @Autowired
    ParticipatesRepository participatesDAO;

//    @RequestMapping(value = "/participatesInsertUserIntoActivity")
//    public ModelAndView insertUserIntoActivity(ModelAndView modelAndView) {
//        User user= new User();
//        user.setId(25);
//        Activity activity= new Activity();
//        activity.setId(43);
//        String result = participatesDAO.insertUserIntoActivity(activity, user);
//        modelAndView.addObject("result", result);
//        modelAndView.setViewName("testComplete");
//        return modelAndView;
//    }
    
}
