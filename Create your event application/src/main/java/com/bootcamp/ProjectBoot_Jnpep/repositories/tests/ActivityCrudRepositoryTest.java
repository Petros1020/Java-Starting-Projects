/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories.tests;

import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityCrudRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pitpr
 */
@Controller
public class ActivityCrudRepositoryTest {

    @Autowired
    ActivityCrudRepository activityCrudDAO;

    @RequestMapping(value = "/activitySelectByIdTest")
    public ModelAndView selectMaxImageName(ModelAndView modelAndView) {
        Optional<Activity> result = activityCrudDAO.findById(32);
        Activity activity = result.get();
        System.out.println(activity.getId());
        System.out.println(activity.getName());
        System.out.println(activity.getType().getName());
        System.out.println(activity.getLocationDescription());
        System.out.println(activity.getDate());
        System.out.println(activity.getTime());
        System.out.println(activity.getLocationX());
        System.out.println(activity.getLocationY());
        System.out.println(activity.getPast());
        System.out.println(activity.getMaxMembers());

        modelAndView.addObject("result", result);
        modelAndView.setViewName("emptySuccess");
        return modelAndView;
    }
}
