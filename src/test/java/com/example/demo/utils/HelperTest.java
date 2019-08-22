package com.example.demo.utils;

import static org.junit.Assert.assertEquals;
import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

public class HelperTest {
    
    @Test
    public void testGetJsonDisk() throws JsonProcessingException {
        Disk disk = new Disk("name");
        assertEquals("{\"id\":null,\"name\":\"name\",\"master\":null,\"currentOwner\":null}", Helper.getJSON(disk));
    }
    
    @Test
    public void testGetJson_User() throws JsonProcessingException {
        User user = new User("name");
        assertEquals(
            "{\"id\":0,\"name\":\"name\",\"credential\":{\"id\":null,\"login\":null,\"password\":null,\"role\":null,\"idClient\":0},\"listDisk\":[]}",
            Helper.getJSON(user));
    }
    
}
