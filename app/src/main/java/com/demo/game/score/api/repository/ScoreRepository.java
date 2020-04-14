package com.demo.game.score.api.repository;

import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
@Transactional
public interface ScoreRepository extends JpaRepository<UserScore, Integer> {

    @Query(value = "SELECT id as userId, score, FIND_IN_SET( score, ( " +
                   "SELECT GROUP_CONCAT( DISTINCT score " +
                   "ORDER BY score DESC ) " +
                   "FROM score ) " +
                   ") AS position  " +
                   "FROM score ORDER BY position LIMIT 20000", nativeQuery = true)
    List<UserPositionDTO> getHighScoreList();

    @Query(value = "SELECT id as userId, score, FIND_IN_SET( score, ( " +
                   "SELECT GROUP_CONCAT( DISTINCT score " +
                   "ORDER BY score DESC ) " +
                   "FROM score ) " +
                   ") AS position  " +
                   "FROM score WHERE id = ?1", nativeQuery = true)
    Optional<UserPositionDTO> getUserScore(Integer userId);
}
