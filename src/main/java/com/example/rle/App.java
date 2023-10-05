package com.example.rle;

import com.example.rle.model.Player;
import com.example.rle.model.Position;
import com.example.rle.model.Team;
import com.example.rle.persistence.PlayerRepository;
import com.example.rle.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {

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
}
