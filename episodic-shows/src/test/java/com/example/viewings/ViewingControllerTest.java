package com.example.viewings;

import com.example.shows.Show;
import com.example.shows.ShowRepository;
import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;
import com.example.users.User;
import com.example.users.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by trainer11 on 5/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ViewingControllerTest {

    @Autowired
    private ViewingRepository viewingRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    MockMvc mvc;

    @Before
    public void setup() {
        viewingRepository.deleteAll();
    }

    @Test
    @Rollback
    @Transactional
    public void createViewing() throws Exception {
        User user = new User();
        user.setEmail("bittu@email.com");
        userRepository.save(user);

        List<User> users = new ArrayList();
        userRepository.findAll().forEach(users::add);


        Show show = new Show();
        show.setName("Spartacus");
        showRepository.save(show);

        List<Show> shows = new ArrayList();
        showRepository.findAll().forEach(shows::add);

        Episode episode = new Episode();
        episode.setShowId(shows.get(0).getId());
        episode.setSeasonNumber(2);
        episode.setEpisodeNumber(5);
        episodeRepository.save(episode);

        List<Episode> episodes = new ArrayList();
        episodeRepository.findAll().forEach(episodes::add);

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("episodeId", episodes.get(0).getId());
                put("updatedAt", "2017-05-20T11:45:34.9182");
                put("timecode", 65);
            }
        };

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        MockHttpServletRequestBuilder request = patch("/users/"+users.get(0).getId()+"/viewings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());

        assertThat(viewingRepository.count(), equalTo(1L));

        MockHttpServletRequestBuilder request2 = get("/users/"+users.get(0).getId()+"/recently-watched")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].show.name", equalTo("Spartacus")))
                .andExpect(jsonPath("$[0].episode.title", equalTo("S2 E5")))
                .andExpect(jsonPath("$[0].episode.episodeNumber", equalTo(5)))
                .andExpect(jsonPath("$[0].timecode", equalTo(65)));
    }
}
