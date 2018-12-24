package com.bootcamp.ProjectBoot_Jnpep.controllers;

import com.bootcamp.ProjectBoot_Jnpep.converters.UserConverter;
import com.bootcamp.ProjectBoot_Jnpep.encryption.JBEncrypt;
import com.bootcamp.ProjectBoot_Jnpep.helper.Helper;
import com.bootcamp.ProjectBoot_Jnpep.models.Activity;
import com.bootcamp.ProjectBoot_Jnpep.models.ImageName;
import com.bootcamp.ProjectBoot_Jnpep.models.User;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ActivityRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.ImageNameRepository;
import com.bootcamp.ProjectBoot_Jnpep.repositories.UserRepository;
import com.bootcamp.ProjectBoot_Jnpep.validators.UserValidator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserConverter userConverter;

    @Autowired
    ImageNameRepository imageNameDAO;
    
    @Autowired
    ActivityRepository ActivityDAO;

    @ModelAttribute("user")
    @RequestMapping(value = "/login")
    public ModelAndView userLogin(ModelAndView modelAndView, String username, String password, HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        // generate a new session
        HttpSession session = request.getSession(true);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userRepository.userLogin(user);
        if (user == null) {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setView(new RedirectView(request.getContextPath() + "/"));
            return modelAndView;
        } else {
            session.setAttribute("user", user);
            modelAndView.setView(new RedirectView(request.getContextPath() + "/user/home"));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/home")
    public ModelAndView insertUserIntoSession(ModelAndView modelAndView, HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        List<Activity> upcoming = ActivityDAO.selectActivitiesUpcoming(user.getId());
        List<Activity> past = ActivityDAO.selectActivitiesPast(user.getId());
        
        System.out.println(upcoming);
        System.out.println(user.getId());
        System.out.println(past);
        
        modelAndView.addObject("upcoming",upcoming);
        modelAndView.addObject("past",past);
        modelAndView.setViewName("userHomePage");

        return modelAndView;
    }

    @RequestMapping(value = "/register")
    public ModelAndView registrationForm(HttpSession session, ModelAndView modelAndView) {
        session.invalidate();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registerPage");
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        modelAndView.setView(new RedirectView(request.getContextPath() + "/"));
        return modelAndView;
    }

    @RequestMapping(value = "/new")
    public ModelAndView userRegister(@Valid User user, BindingResult result, ModelAndView modelAndView, String password1,
            HttpServletRequest request, @RequestParam(value = "imageupload") MultipartFile fileUpload) throws ParseException {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        // generate a new session
        HttpSession session = request.getSession(true);
        if (result.hasErrors()) {
            modelAndView.setViewName("registerPage");

            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (ObjectError error : result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error : result.getFieldErrors()) {
                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            modelAndView.addAllObjects(result2.getModel());
            user.setPassword(null);
            modelAndView.addObject("user", user);

        } else {

            String alternateFileNameString = imageNameDAO.selectMaxImageName();
            ImageName img = new ImageName();
            img.setName(Integer.parseInt(alternateFileNameString));
            imageNameDAO.newImageName(img);
            user.setProfilePic(Helper.saveProfileImage(fileUpload, alternateFileNameString));

            if (!(user.getPassword().equals(password1))) {

                modelAndView.setViewName("registerPage");
                modelAndView.addObject("message", "The passwords don't match");
                user.setPassword(null);
                modelAndView.addObject("user", user);
            } else {
                modelAndView.setViewName("userHomePage");
                String hashed = JBEncrypt.hashpw(user.getPassword(), JBEncrypt.gensalt(12));
                user.setPassword(hashed);
                userRepository.insert(user);
                user.setPassword("");
                session.setAttribute("user", user);
                modelAndView.setView(new RedirectView(request.getContextPath() + "/user/home"));

            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/settings")
    public ModelAndView settingsform(ModelAndView modelAndView, @ModelAttribute("user") User user, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        user = (User) session.getAttribute("user");
        user.getUsername();
        user.getEmail();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userSettings");
        return modelAndView;
    }

//    @RequestMapping(value = "/switchusername")
//    public ModelAndView switchUsername(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request) {
//
//        HttpSession session = request.getSession(false);
//        modelAndView.setViewName("userSettings");
//
//        //Copies the errors to a new model to clear the password
//        if (result.hasErrors()) {
//            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
//            for (ObjectError error : result.getGlobalErrors()) {
//                result2.addError(error);
//            }
//            for (FieldError error : result.getFieldErrors()) {
//                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
//            }
//            modelAndView.addAllObjects(result2.getModel());
//            user.setPassword(null);
//            user.setEmail(((User) session.getAttribute("user")).getEmail());
//            user.setUsername(((User) session.getAttribute("user")).getUsername());
//            modelAndView.addObject("user", user);
//        } else {
//            User currentUser = new User((User) session.getAttribute("user"));
//            currentUser.setPassword(user.getPassword());
//
//            if (userRepository.userConfirm(currentUser)) {
//                currentUser.setUsername(user.getUsername());
//                userRepository.usernameUpdate(currentUser);
//                modelAndView.addObject("usernamemessage", "Your username was updated successfully");
//                ((User) session.getAttribute("user")).setUsername(currentUser.getUsername());
//            } else {
//                modelAndView.addObject("passwordconfirmation1", "Wrong password");
//            }
//            currentUser.setPassword(null);
//            modelAndView.addObject("user", currentUser);
//        }
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/switchemail")
//    public ModelAndView switchEmail(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        modelAndView.setViewName("userSettings");
//
//        //Copies the errors to a new model to clear the password
//        if (result.hasErrors()) {
//            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
//            for (ObjectError error : result.getGlobalErrors()) {
//                result2.addError(error);
//            }
//            for (FieldError error : result.getFieldErrors()) {
//                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
//            }
//            modelAndView.addAllObjects(result2.getModel());
//            user.setPassword(null);
//            user.setEmail(((User) session.getAttribute("user")).getEmail());
//            user.setUsername(((User) session.getAttribute("user")).getUsername());
//            modelAndView.addObject("user", user);
//        } else {
//            User currentUser = new User((User) session.getAttribute("user"));
//            currentUser.setPassword(user.getPassword());
//
//            if (userRepository.userConfirm(currentUser)) {
//                currentUser.setEmail(user.getEmail());
//                userRepository.emailUpdate(currentUser);
//                modelAndView.addObject("emailmessage", "Your e-mail was updated successfully");
//                ((User) session.getAttribute("user")).setEmail(currentUser.getEmail());
//            } else {
//                modelAndView.addObject("passwordconfirmation2", "Wrong password");
//            }
//            currentUser.setPassword(null);
//            modelAndView.addObject("user", currentUser);
//        }
//        return modelAndView;
//    }
    @RequestMapping(value = "/switchpassword")
    public ModelAndView switchPassword(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request, String newpassword, String newpasswordconfirm) {
        HttpSession session = request.getSession(false);
        modelAndView.setViewName("userSettings");

        //Copies the errors to a new model to clear the password
        if (result.hasErrors()) {
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (ObjectError error : result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error : result.getFieldErrors()) {
                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            modelAndView.addAllObjects(result2.getModel());
            user.setPassword(null);
            user.setEmail(((User) session.getAttribute("user")).getEmail());
            user.setUsername(((User) session.getAttribute("user")).getUsername());
            modelAndView.addObject("user", user);
        } else {
            if (!(newpassword.equals(newpasswordconfirm))) {

                modelAndView.addObject("passwordmessage2", "The passwords don't match");
                user.setPassword(null);
                user.setEmail(((User) session.getAttribute("user")).getEmail());
                user.setUsername(((User) session.getAttribute("user")).getUsername());
                modelAndView.addObject("user", user);
            } else {
                User currentUser = new User(( User) session.getAttribute("user"));
                currentUser.setPassword(user.getPassword());

                if (userRepository.userConfirm(currentUser)) {
                    String hashed = JBEncrypt.hashpw(newpassword, JBEncrypt.gensalt(12));
                    currentUser.setPassword(hashed);
                    userRepository.passwordUpdate(currentUser);
                    modelAndView.addObject("passwordmessage", "Your password was updated successfully");
                } else {
                    modelAndView.addObject("passwordconfirmation2", "Wrong password");
                }
                currentUser.setPassword(null);
                modelAndView.addObject("user", currentUser);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request, @RequestParam(value = "imageupload") MultipartFile fileUpload) {

        modelAndView.setViewName("forward:information");
        if (fileUpload.getOriginalFilename().equals("")) {
            modelAndView.setViewName("forward:info");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/information")
    public ModelAndView info(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request, @RequestParam(value = "imageupload") MultipartFile fileUpload) {
        HttpSession session = request.getSession(false);
        modelAndView.setViewName("userSettings");

        //Copies the errors to a new model to clear the password
        if (result.hasErrors()) {
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (ObjectError error : result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error : result.getFieldErrors()) {
                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            modelAndView.addAllObjects(result2.getModel());
            user.setPassword(null);
            user.setProfilePic(((User) session.getAttribute("user")).getProfilePic());
            user.setEmail(((User) session.getAttribute("user")).getEmail());
            user.setUsername(((User) session.getAttribute("user")).getUsername());
            modelAndView.addObject("user", user);
        } else {
            User currentUser = new User((User) session.getAttribute("user"));
            currentUser.setPassword(user.getPassword());

            if (userRepository.userConfirm(currentUser)) {
                String alternateFileNameString = imageNameDAO.selectMaxImageName();
                ImageName img = new ImageName();
                img.setName(Integer.parseInt(alternateFileNameString));
                imageNameDAO.newImageName(img);
                user.setProfilePic(Helper.saveProfileImage(fileUpload, alternateFileNameString));
                currentUser.setEmail(user.getEmail());
                currentUser.setUsername(user.getUsername());
                currentUser.setProfilePic(user.getProfilePic());
                userRepository.infoUpdate(currentUser);
                modelAndView.addObject("infomessage", "Your info was updated successfully");
                session.setAttribute("user", currentUser);

            } else {
                modelAndView.addObject("passwordconfirmation1", "Wrong password");
            }
            currentUser.setPassword(null);
            modelAndView.addObject("user", currentUser);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/info")
    public ModelAndView info(ModelAndView modelAndView, @Valid User user, BindingResult result, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        modelAndView.setViewName("userSettings");

        //Copies the errors to a new model to clear the password
        if (result.hasErrors()) {
            BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(user, result.getObjectName());
            for (ObjectError error : result.getGlobalErrors()) {
                result2.addError(error);
            }
            for (FieldError error : result.getFieldErrors()) {
                result2.addError(new FieldError(error.getObjectName(), error.getField(), null, error.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
            }
            modelAndView.addAllObjects(result2.getModel());
            user.setPassword(null);
            user.setProfilePic(((User) session.getAttribute("user")).getProfilePic());
            user.setEmail(((User) session.getAttribute("user")).getEmail());
            user.setUsername(((User) session.getAttribute("user")).getUsername());
            modelAndView.addObject("user", user);
        } else {
            User currentUser = new User((User) session.getAttribute("user"));
            currentUser.setPassword(user.getPassword());

            if (userRepository.userConfirm(currentUser)) {
                currentUser.setEmail(user.getEmail());
                currentUser.setUsername(user.getUsername());
                currentUser.setProfilePic(user.getProfilePic());
                userRepository.infoUpdate(currentUser);
                modelAndView.addObject("infomessage", "Your info was updated successfully");
                session.setAttribute("user", currentUser);
            } else {
                modelAndView.addObject("passwordconfirmation1", "Wrong password");
            }
            currentUser.setPassword(null);
            modelAndView.addObject("user", currentUser);
        }
        return modelAndView;
    }

//    @RequestMapping(value = "/loginform")
//    public ModelAndView userForm(ModelAndView modelAndView) {
////      (To be fixed)modelAndView.setViewName("jsp-name");
//        User user = new User();
//        modelAndView.addObject("user", user);
//
//        return modelAndView;
//    }
//    @RequestMapping(value = "/validateusername")
//    public ModelAndView validateUsername(ModelAndView modelAndView, User user, BindingResult bindingResult) {
//
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
////          (To be fixed)modelAndView.setViewName("jsp-name");
//            return modelAndView;
//        }
//        modelAndView.addObject("user", user);
////      (To be fixed)modelAndView.setViewName("forward:(controller-name)");
//        return modelAndView;
//    }
//    @RequestMapping(value = "/activityajaxsearch", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
//    public @ResponseBody
//    String getAllGuestsByRest(@RequestParam(value = "form-input-name") String id)
//            throws JsonProcessingException {
//
//        List<User> userList = userRepository.selectUser(id);
//
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(userList));
//        return mapper.writeValueAsString(userList);
//    }
//    @RequestMapping(value = "/insert")
//    public ModelAndView insertUser(User user, ModelAndView modelAndView, HttpServletRequest request, String password2) {
////      (To be fixed)modelAndView.setView(new RedirectView(request.getContextPath()+"/jsp-name"));
//
//        if (!(user.getPassword().equals(password2))) {
//
//            return modelAndView;
//        }
//        String hashed = JBEncrypt.hashpw(user.getPassword(), JBEncrypt.gensalt(12));
//        user.setPassword(hashed);
//        userRepository.insert(user);
//
//        return modelAndView;
//    }
//    @RequestMapping(value = "/list")
//    public ModelAndView userList(ModelAndView modelAndView) {
////      (To be fixed)modelAndView.setViewName("jsp-name");
//        List<User> userList = userRepository.selectAllUsers();
//
//        modelAndView.addObject("userList", userList);
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/searchform")
//    public ModelAndView searchUserForm(ModelAndView modelAndView) {
////      (To be fixed)modelAndView.setViewName("searchForm");
//
//        return modelAndView;
//    }
//    @RequestMapping(value = "/delete")
//    public ModelAndView deleteUserFromList(ModelAndView modelAndView, HttpServletRequest request, String username) {
////      (To be fixed)modelAndView.setViewName("forward:controller-name");
//        userRepository.deleteUser(username);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/search")
//    public ModelAndView searchUser(ModelAndView modelAndView, HttpServletRequest request) {
//        String searchWord = request.getParameter("username");
////      (To be fixed)modelAndView.setViewName("jsp-name");
//        List<User> userList = userRepository.selectUser(searchWord);
//        modelAndView.addObject("userList", userList);
//
//        return modelAndView;
//    }
}
