package com.example.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by ywf on 17-12-11.
 */
public class JacksonDemo2 {
    public static void main(String[] args) throws Exception{
        String json="{\"name\":\"小明\",\"age\":30,\"birthday\":591638400000,\"email\":\"441823286@qq.com\"}";
        ObjectMapper mapper=new ObjectMapper();
        User user=mapper.readValue(json,User.class);
        System.out.println(user);
    }
}
