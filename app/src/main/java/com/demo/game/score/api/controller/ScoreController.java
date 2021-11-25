package com.demo.game.score.api.controller;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.EmptyUserPositionDTO;
import com.demo.game.score.api.controller.response.HighScoreDTO;
import com.demo.game.score.api.controller.response.IUserPosition;
import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/h2")
@Api(tags = "Game Score API - (H2 Implementation)")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(@Qualifier("scoreServiceH2") ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @ApiOperation(value = "Save user points based on user id")
    @PostMapping("/score")
    public void saveUserPoints(@RequestBody @Valid @NotNull UserScoreRequest userPointRequest) {
        this.scoreService.saveUserPoints(userPointRequest);
    }

    @ApiOperation(value = "Get user score and position")
    @GetMapping("/{userId}/position")
    public ResponseEntity<IUserPosition> getUserPosition(@PathVariable @NotNull Integer userId) {

        Optional<UserPositionDTO> userPositionDTO = this.scoreService.getUserPosition(userId);
        return userPositionDTO.isPresent() ? new ResponseEntity<>(userPositionDTO.get(), HttpStatus.OK) :
             new ResponseEntity<>(new EmptyUserPositionDTO(null, null), HttpStatus.OK);
    }

    @ApiOperation(value = "Return the high score list (top 20000)")
    @GetMapping("/highscorelist")
    public HighScoreDTO getHighScoreList() {
        return this.scoreService.getHighScoreList();
    }
}
