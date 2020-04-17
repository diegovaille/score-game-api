package com.demo.game.score.api.service;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.HighScoreDTO;

import java.util.Optional;

/**
 * Interface for the Score Service
 */
public interface ScoreService<T> {

    void saveUserPoints(UserScoreRequest userPointRequest);

    Optional<T> getUserPosition(Integer userId);

    HighScoreDTO getHighScoreList();
}
