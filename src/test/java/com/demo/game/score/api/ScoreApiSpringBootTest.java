package com.demo.game.score.api;

import com.demo.game.score.api.repository.ScoreRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ScoreApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ScoreApiSpringBootTest {

    @Autowired
    private ScoreRepository scoreRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void resetMocks() {
        Mockito.reset(scoreRepository);
    }

}