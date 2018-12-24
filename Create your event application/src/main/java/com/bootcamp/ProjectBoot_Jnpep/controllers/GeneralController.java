package com.bootcamp.ProjectBoot_Jnpep.controllers;

import com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault;
import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityDefaultRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GeneralController {

    @Autowired
    ActivityRepository activityDAO;

    @Autowired
    ActivityDefaultRepository activityDefaultDAO;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        List <ActivitiesDefault> actDef = activityDefaultDAO.selectAllActDef();
        ActivitiesDefault firstAct = actDef.get(0);
        actDef.remove(0);
        actDef.remove(20);
        modelAndView.addObject("firstAct",firstAct);
        modelAndView.addObject("actdef",actDef);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/filter")
    public ModelAndView indexFilter(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/pittest")
    public ModelAndView testIndex(ModelAndView modelAndView) {
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping("/test")
    public String test() {
        return "testleaflet";
    }

}
