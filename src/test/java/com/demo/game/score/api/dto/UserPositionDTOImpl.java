package com.demo.game.score.api.dto;

import com.demo.game.score.api.controller.response.UserPositionDTO;

public class UserPositionDTOImpl implements UserPositionDTO {

    private final Integer userId;
    private final Integer score;
    private final Integer position;

    public UserPositionDTOImpl(Integer userId, Integer score, Integer position) {
        this.userId = userId;
        this.position = position;
        this.score = score;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override
    public Integer getPosition() {
        return position;
    }
}
