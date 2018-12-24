/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.filters;

import com.bootcamp.ProjectBoot_Jnpep.models.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

/**
 *
 * @author karag
 */
@Component
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        Filter.super.init(fc); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession session = request.getSession(false);

//        String css = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/css";
//        String js = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/js";
//        String index = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/";
//        String login = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/user/login";
//        String registration = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/user/new";
//        String registrationContr = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/user/register";
//        String ajaxReq = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/activity/ajaxsearch";
//        String searchAct = "http://51.15.115.175:8080/ProjectBoot_Jnpep-0.0.1-SNAPSHOT/activity/selectActivity";

                String css = "http://localhost:8080/ProjectBoot_Jnpep/css";
        String js = "http://localhost:8080/ProjectBoot_Jnpep/js";
        String index = "http://localhost:8080/ProjectBoot_Jnpep/";
        String login = "http://localhost:8080/ProjectBoot_Jnpep/user/login";
        String registration = "http://localhost:8080/ProjectBoot_Jnpep/user/new";
        String registrationContr = "http://localhost:8080/ProjectBoot_Jnpep/user/register";
        String ajaxReq = "http://localhost:8080/ProjectBoot_Jnpep/activity/ajaxsearch";
        String searchAct = "http://localhost:8080/ProjectBoot_Jnpep/activity/selectActivity";
        
        String url = request.getRequestURL().toString();
        String contP = request.getContextPath();

        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }
        if (user == null && !(url.startsWith(css) || url.startsWith(js) || url.equals(index) || url.equals(login) || url.equals(registration) || url.equals(registrationContr) || url.equals(ajaxReq) || url.startsWith(searchAct))) {
            response.sendRedirect(request.getContextPath());
            System.out.println("oxi comble");
        } else {
            fc.doFilter(request, response);
        }

//        fc.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

}
