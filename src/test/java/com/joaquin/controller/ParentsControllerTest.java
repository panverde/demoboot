package com.joaquin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ParentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void listAllParents() throws Exception {
        this.mvc.perform(get("/parents/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=utf-8"));
    }

    @Test
    public void updateExistParents() throws Exception{

       this.mvc.perform(put("/parents/update/17")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" \t{\n" +
                        "        \"gender\": \"M\",\n" +
                        "        \"firstName\": \"PADRE2010AS\",\n" +
                        "        \"middleName\": \"PADRE2010EAS\",\n" +
                        "        \"lastName\": \"PPADRE2010AS\",\n" +
                        "        \"otherParentDetails\": \"PADRE2010EAS\",\n" +
                        "        \"ltaStudentParents\": [\n" +
                        "            {\n" +
                        "                \"studentId\": 32\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }")).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void createParents() throws  Exception{

        this.mvc.perform(post("/parents/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" \t{\n" +
                        "        \"gender\": \"M\",\n" +
                        "        \"firstName\": \"PADREP454\",\n" +
                        "        \"middleName\": \"PADREP454\",\n" +
                        "        \"lastName\": \"PADREP454\",\n" +
                        "        \"otherParentDetails\": \"PADREP454\",\n" +
                        "        \"ltaStudentParents\": [\n" +
                        "            {\n" +
                        "                \"studentId\": 34\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }")).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteParents() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.delete("/parents/delete/18"))
                .andExpect(status().isNoContent());
    }
}