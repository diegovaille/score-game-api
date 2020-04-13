package com.demo.game.score.api.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( { "userId", "score", "position"})
public interface UserPositionDTO {

    Integer getUserId();

    Integer getScore();

    Integer getPosition();

}
