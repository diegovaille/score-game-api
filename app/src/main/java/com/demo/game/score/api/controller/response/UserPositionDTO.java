package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( { "userId", "score", "position"})
public class UserPositionDTO implements IUserPosition {

    private final Integer userId;
    private final Integer score;
    private Integer position;

    public UserPositionDTO(Integer userId, Integer score) {
        this.userId = userId;
        this.score = score;
    }

    @JsonCreator
    public UserPositionDTO(@JsonProperty("userId") Integer userId,
                           @JsonProperty("score") Integer score,
                           @JsonProperty("position") Integer position) {
        this(userId, score);
        this.position = position;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
