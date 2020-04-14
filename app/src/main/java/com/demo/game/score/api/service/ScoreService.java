package com.demo.game.score.api.service;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.HighScoreDTO;
import com.demo.game.score.api.controller.response.UserPositionDTO;

import java.util.Optional;

/**
 * Interface for the Score Service
 */
public interface ScoreService {

    void saveUserPoints(UserScoreRequest userPointRequest);

    Optional<UserPositionDTO> getUserPosition(Integer userId);

    HighScoreDTO getHighScoreList();
}
