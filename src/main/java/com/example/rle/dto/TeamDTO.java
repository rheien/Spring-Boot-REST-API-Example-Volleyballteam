package com.example.rle.dto;

import com.example.rle.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
public class TeamDTO {
    private String name;

    private String trainer;

    private String location;

    private List<PlayerDTO> players;

    public static TeamDTO of(Team team){
        return TeamDTO.builder()
                .name(team.getName())
                .trainer(team.getTrainer())
                .location(team.getLocation())
                .players(team.getPlayers().stream()
                        .map(PlayerDTO::of)
                        .toList())
                .build();
    }
}
