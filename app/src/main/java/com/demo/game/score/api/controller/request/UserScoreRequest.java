package com.demo.game.score.api.controller.request;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserScoreRequest {

    @Valid
    @NotNull(message = "UserId must not be null")
    @ApiParam(value = "User Id", required = true)
    private Integer userId;

    @Valid
    @NotNull(message = "Points must not be null")
    @ApiParam(value = "User Points", required = true)
    private Integer points;
}