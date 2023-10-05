package com.example.rle.service;

import com.example.rle.exception.DuplicatePlayerException;
import com.example.rle.exception.InvalidPlayerPositionException;
import com.example.rle.exception.PlayerNotFoundException;
import com.example.rle.exception.TeamNotFoundException;
import com.example.rle.model.Player;
import com.example.rle.model.Position;
import com.example.rle.model.Team;
import com.example.rle.persistence.PlayerRepository;
import com.example.rle.persistence.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getAllPlayersFromTeam(String name) {
        return playerRepository.findByTeamName(name);
    }

    public Team getTeam(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findById(teamName);
        if (optionalTeam.isEmpty()) {
            throw new TeamNotFoundException(teamName);
        }
        return optionalTeam.get();
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team createOrUpdateTeam(Team newTeam) {
        Optional<Team> existingTeam = teamRepository.findById(newTeam.getName());
        if (existingTeam.isEmpty()) {
            return teamRepository.save(newTeam);
        } else {
            Team teamToUpdate = existingTeam.get();
            teamToUpdate.setTrainer(newTeam.getTrainer());
            teamToUpdate.setLocation(newTeam.getLocation());
            return teamRepository.save(teamToUpdate);
        }
    }

    public Team addPlayerToTeam(Player player) {
        if (player.getTeam() == null) {
            throw new IllegalArgumentException();
        }

        Optional<Team> optionalTeam = teamRepository.findById(player.getTeam().getName());
        if (optionalTeam.isEmpty()) {
            throw new TeamNotFoundException(player.getTeam().getName());
        } else {
            Team team = optionalTeam.get();

            if (isPlayerExistsInTeam(player, team)) {
                throw new DuplicatePlayerException(player.getName());
            }

            if (!isValidPlayerPosition(player.getPosition())) {
                throw new InvalidPlayerPositionException(player.getPosition());
            }

            player.setTeam(team);
            playerRepository.save(player);

            List<Player> players = team.getPlayers();
            players.add(player);
            team.setPlayers(players);
            return teamRepository.save(team);
        }
    }

    public Team removePlayerFromTeam(Player player) {
        if (player.getTeam() == null) {
            throw new IllegalArgumentException();
        }

        Optional<Team> optionalTeam = teamRepository.findById(player.getTeam().getName());
        if (optionalTeam.isEmpty()) {
            throw new TeamNotFoundException(player.getTeam().getName());
        } else {
            Team team = optionalTeam.get();

            Player playerToRemove = team.getPlayers().stream()
                    .filter(existingPlayer ->
                            existingPlayer.getName().equals(player.getName()) &&
                                    existingPlayer.getPosition().equals(player.getPosition()))
                    .findFirst()
                    .orElseThrow(() -> new PlayerNotFoundException(player.getId()));

            List<Player> players = team.getPlayers();
            players.remove(playerToRemove);
            team.setPlayers(players);

            playerToRemove.setTeam(null);
            playerRepository.save(playerToRemove);
            return teamRepository.save(team);
        }
    }

    private boolean isPlayerExistsInTeam(Player player, Team team) {
        return team.getPlayers().stream()
                .anyMatch(existingPlayer ->
                        existingPlayer.getName().equals(player.getName()) &&
                                existingPlayer.getPosition().equals(player.getPosition()));
    }

    private boolean isValidPlayerPosition(String position) {
        for (Position enumValue : Position.values()) {
            if (enumValue.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
}
