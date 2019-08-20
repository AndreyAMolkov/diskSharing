package com.example.demo.utils;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.constraints.NotNull;

public class Helper {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static String getJSON(@NotNull Disk disk) throws JsonProcessingException {
        return mapper.writeValueAsString(disk);
    }
    
    public static String getJSON(@NotNull User user) throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }
}
