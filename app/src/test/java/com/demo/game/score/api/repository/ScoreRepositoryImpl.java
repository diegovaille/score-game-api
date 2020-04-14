package com.demo.game.score.api.repository;

import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Due to the fact that Spring creates a proxy of the ScoreRepository interface, Mockito needs an actual class to be able to Mock
 */
public class ScoreRepositoryImpl implements ScoreRepository {

    @Override
    public List<UserPositionDTO> getHighScoreList() {
        return null;
    }

    @Override
    public Optional<UserPositionDTO> getUserScore(Integer userId) {
        return Optional.empty();
    }

    @Override
    public List<UserScore> findAll() {
        return null;
    }

    @Override
    public List<UserScore> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserScore> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserScore> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(UserScore userScore) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserScore> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserScore> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserScore> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserScore> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserScore> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<UserScore> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserScore getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends UserScore> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserScore> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserScore> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserScore> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserScore> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserScore> boolean exists(Example<S> example) {
        return false;
    }
}
