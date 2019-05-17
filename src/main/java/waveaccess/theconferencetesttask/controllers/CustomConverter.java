package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomConverter implements Converter<String, List<User>> {

    @Autowired
    private UserService userService;

    @Override
    public List<User> convert(String source) {
        String[] split = source.split(", ");
        List<User> authors = new ArrayList<>();
        for (String s : split) {
            authors.add(userService.findUserByUsername(s));
        }
        return authors;
    }
}
