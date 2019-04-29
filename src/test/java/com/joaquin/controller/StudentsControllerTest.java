package com.joaquin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public class StudentsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void listAllStudents() throws Exception {
        this.mvc.perform(get("/students/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json; charset=utf-8"));

    }

    @Test
    public void createStudent() throws Exception{

        this.mvc.perform(post("/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"gender\": \"M\",\n" +
                        "        \"firstName\": \"STUDENT2019\",\n" +
                        "        \"middleName\": \"STUDENT2019\",\n" +
                        "        \"lastName\": \"STUDENT2019\",\n" +
                        "        \"dateOfBirth\": \"1992-06-04T00:00:00.000+0000\",\n" +
                        "        \"otherStudentDetails\": \"STUDENT2019\",\n" +
                        "        \"ltaStudentParents\": [\n" +
                        "            {\n" +
                        "                \"parentId\": 12\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }")).andExpect(status().isOk())
                            .andReturn();


    }

    @Test
    public void updateExistStudent() throws Exception{

        this.mvc.perform(put("/students/update/14")
                .contentType(MediaType.APPLICATION_JSON)
                .content("    {\n" +
                        "        \"gender\": \"M\",\n" +
                        "        \"firstName\": \"STUDENTUPDATELASR\",\n" +
                        "        \"middleName\": \"STUDENTUPDATELASR\",\n" +
                        "        \"lastName\": \"STUDENTUPDATELASR\",\n" +
                        "        \"dateOfBirth\": \"1992-06-04T00:00:00.000+0000\",\n" +
                        "        \"otherStudentDetails\": \"arquiero aprender PRU\",\n" +
                        "        \"ltaStudentParents\": [\n" +
                        "            {\n" +
                        "                \"parentId\": 12\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }")).andExpect(status().isOk())
                .andReturn();


    }

    @Test
    public void deleteStudents() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/students/delete/44"))
                .andExpect(status().isNoContent());
    }
}