package com.example.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by trainer11 on 5/22/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EventRepository eventRepository;


    @Before
    public void setup(){
        eventRepository.deleteAll();
    }

    @Test
    public void eventsGetPostTest() throws Exception {

        Long count = eventRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("type", "play");
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("userId", 21);
                put("showId", 44);
                put("episodeId", 12);
                put("data", put("offset", 12));
            }
        };
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request1 = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.type", notNullValue())));

        assertThat(eventRepository.count(), equalTo(count+1));

        MockHttpServletRequestBuilder request2 = get("/recent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].type", equalTo("play")));
    }
}
