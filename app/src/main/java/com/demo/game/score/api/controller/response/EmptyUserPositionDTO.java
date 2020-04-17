package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EmptyUserPositionDTO extends UserPositionDTO {

    public EmptyUserPositionDTO(Integer userId, Integer score) {
        super(userId, score);
    }

    @Override
    @JsonIgnore
    public Integer getUserId() {
        return null;
    }

    @Override
    @JsonIgnore
    public Integer getScore() {
        return null;
    }

    @Override
    @JsonIgnore
    public Integer getPosition() {
        return null;
    }
}