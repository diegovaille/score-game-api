package com.demo.game.score.api.controller.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "HighScoreResponse")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HighScoreDTO<T extends IUserPosition> {

    private List<T> highScoreList;
}
