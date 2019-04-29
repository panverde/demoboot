package com.joaquin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FamilyMembersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void listFamilyMembersByFamilyId() throws Exception{

        this.mvc.perform(get("/familymembers/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=utf-8"));

    }

    @Test
    public void listAllFamilyMembers() throws Exception{
        this.mvc.perform(get("/familymembers/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=utf-8"));
    }

    @Test
    public void createFamilyMembers() throws Exception{

        this.mvc.perform(post("/familymembers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\t{\n" +
                        "        \"familyId\": {\n" +
                        "            \"familyId\": 16\n" +
                        "        },\n" +
                        "        \"parentOrStudentMember\": \"FAMILIA201906\",\n" +
                        "        \"parentId\": {\n" +
                        "            \"parentId\": 28\n" +
                        "        },\n" +
                        "        \"studentId\": {\n" +
                        "            \"studentId\": 10\n" +
                        "        }\n" +
                        "   }")).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void updateExistFamilyMembers() throws Exception{

        this.mvc.perform(put("/familymembers/update/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\t{\n" +
                        "        \"familyId\": {\n" +
                        "            \"familyId\": 11\n" +
                        "        },\n" +
                        "        \"parentOrStudentMember\": \"FAMILYMEMEBERS1104\",\n" +
                        "        \"parentId\": {\n" +
                        "            \"parentId\": 8\n" +
                        "        },\n" +
                        "        \"studentId\": {\n" +
                        "            \"studentId\": 22\n" +
                        "        }\n" +
                        "   }")).andExpect(status().isOk())
                .andReturn();


    }

    @Test
    public void deleteFamilyMembers() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/familymembers/delete/11"))
                .andExpect(status().isNoContent());
    }
}