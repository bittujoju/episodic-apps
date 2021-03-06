package com.example.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer11 on 5/17/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup(){
        userRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void usersGetPostTest() throws Exception {

        Long count = userRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("email", "joe8@example.com");
            }
        };
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request1 = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.id", notNullValue())));

        assertThat(userRepository.count(), equalTo(count+1));

        MockHttpServletRequestBuilder request2 = get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", notNullValue()));
    }
}
