package com.demo.game.score.api.service;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.HighScoreDTO;
import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import com.demo.game.score.api.repository.ScoreRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Async
    @Override
    public void saveUserPoints(UserScoreRequest userPointRequest) {
        this.scoreRepository.save(new UserScore(userPointRequest.getUserId(), userPointRequest.getPoints()));
    }

    @Override
    @Async
    public Optional<UserPositionDTO> getUserPosition(Integer userId) {
        return scoreRepository.getUserScore(userId);
    }

    @Override
    @Async
    public HighScoreDTO getHighScoreList() {
        return new HighScoreDTO(scoreRepository.getHighScoreList());
    }
}
