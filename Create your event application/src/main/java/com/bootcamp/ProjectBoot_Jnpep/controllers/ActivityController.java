package com.bootcamp.ProjectBoot_Jnpep.controllers;

import com.bootcamp.ProjectBoot_Jnpep.helper.Helper;
import com.bootcamp.ProjectBoot_Jnpep.models.ActivitiesDefault;
import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import com.bootcamp.ProjectBoot_Jnpep.models.Participates;
import com.bootcamp.ProjectBoot_Jnpep.models.ImageName;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityCrudRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityDefaultRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ImageNameRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.UserRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ParticipatesRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ActivityRepository activityDAO;

    @Autowired
    ActivityCrudRepository activityCrudRepository;

    @Autowired
    ActivityDefaultRepository activityDefaultDAO;

    @Autowired
    ImageNameRepository imageNameDAO;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParticipatesRepository participatesRepository;

    @GetMapping(value = "/create")
    public ModelAndView insertActivityForm(ModelAndView modelAndView) {
        List<ActivitiesDefault> actList = activityDefaultDAO.selectAllActDef();
        modelAndView.setViewName("newActivityForm");
        modelAndView.addObject("activity", new Activity());
        modelAndView.addObject("actList", actList);
        return modelAndView;
    }

    @RequestMapping(value = "/selectActivity")
    public ModelAndView showActivityPage(ModelAndView modelAndView, int id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        boolean participates = false;
        if (user != null) {
            user = userRepository.findUser(user.getId());
            Activity activity = (activityCrudRepository.findById(id)).get();
            participates = participatesRepository.findByUserAndActivity(activity, user, entityManager);
        }

        if (participates) {
            modelAndView.setViewName("forward:page/" + id);
        } else {
            modelAndView.setViewName("forward:generalinfo/" + id);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/generalinfo/{activityId}")
    public ModelAndView selectActivity(ModelAndView modelAndView, HttpServletRequest request, @PathVariable("activityId") Integer activityId) {
        Optional<Activity> result = activityCrudRepository.findById(activityId);
        Activity activity = result.get();

        long members = participatesRepository.countByActivityId(activity);

        modelAndView.addObject("members", members);
        modelAndView.addObject("activity", activity);
        modelAndView.setViewName("activityJoin");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView createNewActivity(ModelAndView modelAndView, Activity activity, BindingResult bindingResult,
            String lat, String longg, String date, String time,
            HttpServletRequest request,
            @RequestParam(value = "imageupload") MultipartFile fileUpload) throws ParseException, MalformedURLException, IOException {

        SimpleDateFormat dob = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = dob.parse(date);
        java.sql.Date date2 = new java.sql.Date(date1.getTime() + (1000 * 60 * 60 * 24));

        String filename = fileUpload.getOriginalFilename();

        if (!filename.equals("")) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://51.15.115.175:8080/JnpepImages/Upload");
            File file = Helper.convert(fileUpload);
            FileBody uploadFilePart = new FileBody(file);
            StringBody uploadFileDirectory = new StringBody("activityimages/");

            String alternateFileNameString = imageNameDAO.selectMaxImageName();
            ImageName img = new ImageName();
            img.setName(Integer.parseInt(alternateFileNameString));
            imageNameDAO.newImageName(img);

            StringBody alternateFileName = new StringBody(alternateFileNameString);
            MultipartEntity reqEntity = new MultipartEntity();

            reqEntity.addPart("file", uploadFilePart);
            reqEntity.addPart("filecategory", uploadFileDirectory);
            reqEntity.addPart("alternatefilename", alternateFileName);

            httpPost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httpPost);
            String responseXml = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            activity.setImage("http://51.15.115.175:8080/jnpepfiles/activityimages/" + alternateFileNameString);
        } else {
            filename = "keno";
            ActivitiesDefault actdef = activityDefaultDAO.selectActDef(activity.getType().getId());
            activity.setImage(actdef.getDefaultImageUrl());
        }

        activity.setPast(0);
        activity.setTime(time);
        activity.setDate(date2);
        activity.setLocationX(lat);
        activity.setLocationY(longg);
        activityDAO.insertActivity(activity);
        modelAndView.setView(new RedirectView(request.getContextPath() + "/activity/join/"+activity.getId()));

        return modelAndView;
    }

    @RequestMapping(value = "/ajaxsearch", headers = "Accept=*/*", produces = "application/json")
    public @ResponseBody
    String getAllActivitiesByRest(String type)
            throws JsonProcessingException {

        List<Activity> activityList = activityDAO.selectAjaxActivities(type);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(activityList);
    }

    @RequestMapping(value = "/mapReturn")
    public ModelAndView mapReturn(ModelAndView modelAndView) {
         modelAndView.setViewName("testleaflet");
//        modelAndView.setViewName("nav");

        return modelAndView;
    }

    @RequestMapping(value = "/mapShow")
    public ModelAndView mapShow(ModelAndView modelAndView) {
        // modelAndView.setViewName("leafletmapstest");
        modelAndView.setViewName("testleafletShowLoc");

        return modelAndView;
    }

    @RequestMapping(value = "/join/{activityId}")
    public ModelAndView activityJoin(ModelAndView modelAndView, HttpServletRequest request, @PathVariable("activityId") Integer activityId) {
        HttpSession session = request.getSession(false);
        User user = userRepository.findUser(((User) session.getAttribute("user")).getId());
        Activity activity = (activityCrudRepository.findById(activityId)).get();
        Participates participates = new Participates(activity, user);
        participatesRepository.save(participates);
        modelAndView.setView(new RedirectView(request.getContextPath() + "/activity/page/" + activityId));

        return modelAndView;
    }

    @RequestMapping(value = "/leave/{activityId}")
    public ModelAndView activityLeave(ModelAndView modelAndView, HttpServletRequest request, @PathVariable("activityId") Integer activityId) {
        HttpSession session = request.getSession(false);
        User user = userRepository.findUser(((User) session.getAttribute("user")).getId());
        Activity activity = (activityCrudRepository.findById(activityId)).get();
        Integer participatesId = participatesRepository.findIdByUserAndActivity(activity, user, entityManager);
        if (participatesId != 0) {
            Optional<Participates> participatesOptional = participatesRepository.findById(participatesId);
            Participates participates = participatesOptional.get();
            participatesRepository.delete(participates);

        }
        modelAndView.setView(new RedirectView(request.getContextPath() + "/activity/selectActivity?id=" + activityId));

//        modelAndView.setViewName("forward:selectActivity?id=" + activityId);
        return modelAndView;
    }

    @RequestMapping(value = "/page/{activityId}")
    public ModelAndView activityPage(ModelAndView modelAndView, HttpServletRequest request, @PathVariable("activityId") Integer activityId) {
        Optional<Activity> result = activityCrudRepository.findById(activityId);
        Activity activity = result.get();
        List<User> userList = userRepository.selectUserParticipates(activity.getId());
        long members=participatesRepository.countByActivityId(activity);
        modelAndView.addObject("members",members);
        modelAndView.addObject("userlist",userList);
        modelAndView.addObject("activity", activity);
        modelAndView.setViewName("activityPage");
        return modelAndView;
    }
    
    @RequestMapping(value="/whatever")
    public void getChat(Integer activityId){
        System.out.println(activityId);
        activityDAO.getChat(activityId);
    }
}
