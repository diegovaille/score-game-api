package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder( { "userId", "score", "position"})
@NoArgsConstructor
@Data
public class UserPositionDTO implements IUserPosition {

    private Integer userId;
    private Integer score;
    private Integer position;

    public UserPositionDTO(Integer userId, Integer score) {
        this.userId = userId;
        this.score = score;
    }
}
