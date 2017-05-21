package com.example.shows;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer11 on 5/20/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowsControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ShowRepository showRepository;

    @Before
    public void setup(){
        showRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void showsGetPostTest() throws Exception {

        Long count = showRepository.count();

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> payload = new HashMap<String, Object>(){
            {
                put("name", "Spartacus");
            }
        };
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request1 = post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.id", notNullValue())));

        assertThat(showRepository.count(), equalTo(count+1));

        MockHttpServletRequestBuilder request2 = get("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", notNullValue()));
    }
}
