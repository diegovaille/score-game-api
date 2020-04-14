package com.demo.game.score.api.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserScoreRequest {

    private final Integer userId;
    private final Integer points;

    @JsonCreator
    public UserScoreRequest(@JsonProperty("userId") Integer userId,
                            @JsonProperty("points") Integer points) {
        this.userId = userId;
        this.points = points;
    }

    @NotNull(message = "UserId must not be null")
    public Integer getUserId() {
        return userId;
    }

    @NotNull(message = "Points must not be null")
    public Integer getPoints() {
        return points;
    }
}
