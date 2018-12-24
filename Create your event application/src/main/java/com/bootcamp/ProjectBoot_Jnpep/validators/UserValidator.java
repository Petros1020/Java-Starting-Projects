/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.validators;

import com.bootcamp.ProjectBoot_Jnpep.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author pitpr
 */
@Component
public class UserValidator implements Validator {
	
	

    @Override
    public boolean supports(Class type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	User user = (User) o;
        String password = user.getPassword().trim();
        String username=user.getUsername().trim();
        if (password.length()<5) {
            errors.rejectValue("password", "password.error");

        }
        
    }
}
