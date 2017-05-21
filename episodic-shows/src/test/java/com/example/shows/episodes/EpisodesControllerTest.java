package com.example.shows.episodes;

import com.example.shows.Show;
import com.example.shows.ShowRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer11 on 5/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EpisodesControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    ShowRepository showRepository;

    @Before
    public void setup(){
        episodeRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void showsGetPostTest() throws Exception {
        Show show = new Show();
        show.setName("Spartacus");
        showRepository.save(show);

        List<Show> shows = new ArrayList();
        showRepository.findAll()
                .forEach(shows::add);

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("seasonNumber", "1");
                put("episodeNumber", "5");
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request1 = post("/shows/" + shows.get(0).getId() + "/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.seasonNumber", equalTo(1)))
                .andExpect(jsonPath("$.episodeNumber", equalTo(5)))
                .andExpect(jsonPath("$.title", equalTo("S1 E5")));

        assertThat(episodeRepository.count(), equalTo(1L));

        MockHttpServletRequestBuilder request2 = get("/shows/" + shows.get(0).getId() + "/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].seasonNumber", equalTo(1)))
                .andExpect(jsonPath("$[0].episodeNumber", equalTo(5)))
                .andExpect(jsonPath("$[0].title", equalTo("S1 E5")));
    }
}
