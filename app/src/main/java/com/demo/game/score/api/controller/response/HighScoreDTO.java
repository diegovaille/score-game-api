package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HighScoreDTO {

    private final List<UserPositionDTO> highScoreList;

    public HighScoreDTO(List<UserPositionDTO> highScoreList) {
        this.highScoreList = highScoreList;
    }

    @JsonProperty("highscorelist")
    public List<UserPositionDTO> getHighScoreList() {
        return highScoreList;
    }
}
