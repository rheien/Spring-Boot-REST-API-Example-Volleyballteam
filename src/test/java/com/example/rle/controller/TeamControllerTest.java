package com.example.rle.controller;

import com.example.rle.configuration.JpaConfig;
import com.example.rle.model.Player;
import com.example.rle.model.Position;
import com.example.rle.model.Team;
import com.example.rle.persistence.PlayerRepository;
import com.example.rle.persistence.TeamRepository;
import com.example.rle.service.TeamService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TeamController.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
//@Import(JpaConfig.class)
public class TeamControllerTest {

    private static final String ENDPOINT_PATH = "/api/teams";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @MockBean
    private TeamService teamService;


@BeforeEach
    public void setUp(){
        Team team = new Team();
        team.setName("Füchse");
        team.setTrainer("Hermann");
        team.setLocation("Berlin");
        teamRepository.save(team);

        Player player = new Player();
        player.setId(1L);
        player.setName("Hans");
        player.setPosition(Position.L.getPosition());
        player.setTeam(team);
        playerRepository.save(player);

        List<Player> players = team.getPlayers();
        players.add(player);
        team.setPlayers(players);
        teamRepository.save(team);

        Team team2 = new Team();
        team2.setName("Dolphin");
        team2.setTrainer("Joos");
        team2.setLocation("Stuttgart");
        teamRepository.save(team2);
    }

    @Test
    public void givenPlayers_whenGetPlayers_thenStatus200() throws Exception {

        mvc.perform(get(ENDPOINT_PATH + "/players")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void givenTeams_whenGetTeams_thenStatus200() throws Exception {

        mvc.perform(
                get("/api/teams")
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }


    /*@Test
    public void addPlayerToTeam_shouldReturn_201Created() throws Exception{
        Player newPlayer = new Player();
        newPlayer.setName("Bui");
        newPlayer.setPosition(Position.RH.getPosition());

        Team team = new Team();
        team.setName("Füchse");
        newPlayer.setTeam(team);

        Mockito.when(teamService.addPlayerToTeam(newPlayer)).thenReturn(team);

        String requestBody = objectMapper.writeValueAsString(newPlayer);

        mvc.perform(
                post(ENDPOINT_PATH + "/team-management/players")
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(requestBody));
    }*/

}
