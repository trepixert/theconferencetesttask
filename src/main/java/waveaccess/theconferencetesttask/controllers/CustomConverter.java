package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.UserService;

@Component
public class CustomConverter implements Converter<String, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(String source) {
        return userService.findUserByUsername(source);
    }
}
