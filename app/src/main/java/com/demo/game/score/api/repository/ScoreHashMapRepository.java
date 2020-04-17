package com.demo.game.score.api.repository;

import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository("hashMapRepository")
public class ScoreHashMapRepository {

    private static final int MAX_SIZE = 20000;
    // Using ConcurrentHashMap for thread safe to keep lock down on write
    private final ConcurrentHashMap<Integer, Integer> scoreHashMap;

    public ScoreHashMapRepository() {
        scoreHashMap = new ConcurrentHashMap<>();
        Random r = new Random();
        // Creating a database of 10000 users with random score between 1 and 10000
        for(int i=0; i < 10000; i++) {
            scoreHashMap.put(i, r.nextInt(10000));
        }

    }

    public List<UserPositionDTO> getHighScoreList() {
        return applyRanking(scoreHashMap.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(MAX_SIZE)
                    .map((entry) -> { return new UserPositionDTO(entry.getKey(), entry.getValue()); })
                .collect(Collectors.toList()));
    }

    public Optional<UserPositionDTO> getUserScore(Integer userId) {
        List<UserPositionDTO> userPositionDTOList =
                applyRanking(scoreHashMap.entrySet().stream()
                                         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                                         .map((entry) -> { return new UserPositionDTO(entry.getKey(), entry.getValue()); })
                                         .collect(Collectors.toList()));

        return userPositionDTOList.stream().filter(userPositionDTO -> userPositionDTO.getUserId().equals(userId)).findFirst();
    }

    private List<UserPositionDTO> applyRanking(List<UserPositionDTO> userPositionDTOList) {
        final int[] currentRank = {1};
        final int[] numbersInRank = {0};
        final UserPositionDTO[] previousUser = {null};
        userPositionDTOList.stream().forEach(userPositionDTO -> {
            if (previousUser[0] == null
                || !previousUser[0].getScore().equals(userPositionDTO.getScore()))
            {
                currentRank[0] += numbersInRank[0];
                userPositionDTO.setPosition(currentRank[0]++);
                numbersInRank[0] = 0;
            }
            else {
                userPositionDTO.setPosition(previousUser[0].getPosition());
                numbersInRank[0]++;
            }

            previousUser[0] = userPositionDTO;
            System.out.println(userPositionDTO);
        });

        return userPositionDTOList;
    }

    public void save(UserScore userScore) {
        scoreHashMap.put(userScore.getUserId(), userScore.getScore());
    }
}
