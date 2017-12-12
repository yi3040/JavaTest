package com.example.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ywf on 17-12-11.
 */
public class JacksonDemo {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("小明");
        user.setEmail("441823286@qq.com");
        user.setAge(30);
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(sp.parse("1988-10-01"));

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(user);
        System.out.println(json);
        List<User> users = new ArrayList<User>();
        users.add(user);
        String jsonList=mapper.writeValueAsString(users);
        System.out.println(jsonList);
    }
}
