/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.repositories.tests;

import com.bootcamp.ProjectBoot_Jnpep.repositories.ImageNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pitpr
 */
@Controller
public class ActivityDefaultRepositoryTest {

    @Autowired
    ImageNameRepository imageNameDAO;

    @RequestMapping(value = "/activitySelectMaxImageName")
    public ModelAndView selectMaxImageName(ModelAndView modelAndView) {
        String result = imageNameDAO.selectMaxImageName();
        modelAndView.addObject("result", result);
        modelAndView.setViewName("testComplete");
        return modelAndView;
    }
}
