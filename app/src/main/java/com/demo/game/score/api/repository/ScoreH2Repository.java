package com.demo.game.score.api.repository;

import com.demo.game.score.api.controller.response.IUserPosition;
import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("h2Repository")
@Profile("!test")
@Transactional
public interface ScoreH2Repository extends JpaRepository<UserScore, Integer> {

    @Query(value = "SELECT s2.user_id as userId, s2.score, 1 + (SELECT count( * ) FROM scores s1 WHERE s1.score > s2.score ) AS position FROM " +
                   "scores s2 ORDER BY position LIMIT 20000", nativeQuery = true)
    List<IUserPosition> getHighScoreList();

    @Query(value = "SELECT s2.user_id as userId, s2.score, 1 + (SELECT count( * ) FROM scores s1 WHERE s1.score > s2.score ) AS position FROM " +
                   "scores s2 WHERE s2.user_id = ?1", nativeQuery = true)
    Optional<IUserPosition> getUserScore(Integer userId);
}
