package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;

@JsonPropertyOrder( { "userId", "score", "position"})
@ApiModel(value = "UserScoreResponse")
public interface IUserPosition {

    Integer getUserId();

    Integer getScore();

    Integer getPosition();
}
