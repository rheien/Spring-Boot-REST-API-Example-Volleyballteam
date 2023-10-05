package com.example.rle.dto;

import com.example.rle.model.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
public class PlayerDTO {
    private String id;

    private String name;

    private String position;

    public static PlayerDTO of(Player player) {
        return PlayerDTO.builder()
                .id(player.getId().toString())
                .name(player.getName())
                .position(player.getPosition())
                .build();
    }

}
