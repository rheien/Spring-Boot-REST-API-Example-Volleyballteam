package com.example.rle.controller;

import com.example.rle.dto.TeamDTO;
import com.example.rle.dto.PlayerDTO;
import com.example.rle.model.Player;
import com.example.rle.model.Team;
import com.example.rle.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/players")
    ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<Player> players = teamService.getAllPlayers();
        var responseEntities = players.stream()
                .map(PlayerDTO::of).toList();
        return new ResponseEntity<>(responseEntities, HttpStatus.OK);
    }

    @GetMapping("/{name}/players")
    ResponseEntity<List<PlayerDTO>> getAllPlayersFromTeam(@PathVariable String name) {
        List<Player> players = teamService.getAllPlayersFromTeam(name);
        var responseEntities = players.stream()
                .map(PlayerDTO::of).toList();
        return new ResponseEntity<>(responseEntities, HttpStatus.OK);
    }


    @GetMapping("/players/{playerId}")
    ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long playerId) {
        Player player = teamService.getPlayerById(playerId);
        return new ResponseEntity<>(PlayerDTO.of(player), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    ResponseEntity<TeamDTO> getTeam(@PathVariable String name) {
        Team team = teamService.getTeam(name);
        return new ResponseEntity<>(TeamDTO.of(team), HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        var responseEntities = teams.stream()
                .map(TeamDTO::of).toList();
        return new ResponseEntity<>(responseEntities, HttpStatus.OK);
    }

    @PostMapping("/team-management")
    ResponseEntity<TeamDTO> createOrUpdateTeam(@RequestBody Team team) {
        Team createOrUpdateTeamData = teamService.createOrUpdateTeam(team);
        return new ResponseEntity<>(TeamDTO.of(createOrUpdateTeamData), HttpStatus.OK);
    }

    @PostMapping("/team-management/players")
    ResponseEntity<TeamDTO> addPlayerToTeam(@RequestBody Player player) {
        Team addPlayerDataToTeam = teamService.addPlayerToTeam(player);
        return new ResponseEntity<>(TeamDTO.of(addPlayerDataToTeam), HttpStatus.OK);
    }

    @DeleteMapping("/team-management/players")
    ResponseEntity<TeamDTO> removePlayerFromTeam(@RequestBody Player player) {
        Team removePlayerDataFromTeam = teamService.removePlayerFromTeam(player);
        return new ResponseEntity<>(TeamDTO.of(removePlayerDataFromTeam), HttpStatus.OK);
    }
}
