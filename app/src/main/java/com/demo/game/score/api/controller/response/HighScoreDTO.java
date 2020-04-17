package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiModel(value = "HighScoreResponse")
public class HighScoreDTO<T extends IUserPosition> {

    private final List<T> highScoreList;

    public HighScoreDTO(List<T> highScoreList) {
        this.highScoreList = highScoreList;
    }

    @JsonProperty("highscorelist")
    public List<T> getHighScoreList() {
        return highScoreList;
    }
}
