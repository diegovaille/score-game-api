package com.demo.game.score.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "scores", indexes = {@Index(columnList = "user_id", unique = true)})
public class UserScore implements Serializable {

    private int userId;
    private Integer score;

    public UserScore() {
    }

    public UserScore(int userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserScore userScore = (UserScore) o;
        return userId == userScore.userId &&
               Objects.equals(score, userScore.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score);
    }
}
