package com.bootcamp.ProjectBoot_Jnpep.converters;

import com.bootcamp.ProjectBoot_Jnpep.models.User;
import com.bootcamp.ProjectBoot_Jnpep.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<Integer, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User convert(Integer id) {

        return userRepository.findUser(id);
    }

}
