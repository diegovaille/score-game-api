package com.demo.game.score.api.steps;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import com.demo.game.score.api.dto.UserPositionDTOImpl;
import com.demo.game.score.api.repository.ScoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScoreSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions resultActions;

    @Then("the client receives status code of {int}")
    public void thenTheClientReceivesStatusCodeOf(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @When("client sends score with userId {int} and points {int}")
    public void clientSendsScoreWithUserIdAndPoints(int userId, int points) throws Exception {

        UserScoreRequest request = new UserScoreRequest(userId, points);
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/score")
                                                .content(objectMapper.writeValueAsString(request))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }

    @When("client sends score with userId NULL and points {int}")
    public void clientSendsScoreWithUserIdNULLAndPoints(int points) throws Exception {
        UserScoreRequest request = new UserScoreRequest(null, points);
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/score")
                                                .content(objectMapper.writeValueAsString(request))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }

    @Given("there is a userId {int} with {int} points and position {int} in the database")
    public void thereIsAUserIdWithPointsAndPositionInTheDatabase(int userId, int points, int position) {
        UserPositionDTO highScoreDTO = new UserPositionDTOImpl(userId, points, position);

        when(scoreRepository.getUserScore(userId)).thenReturn(Optional.of(highScoreDTO));
    }

    @When("client sends a GET with userId {int}")
    public void clientsSendsAGETWithUserId(Integer userId) throws Exception {
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .get(String.join("/", "/" + userId.toString(), "position") )
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));

    }

    @And("the response has a body object with userId {int}, points {int} and position {int}")
    public void theResponseHasABodyObjectWithUserIdPointsAndPosition(int userId, int points, int position) throws IOException {
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertNotNull(responseBody);

        UserPositionDTO userPositionDTO = objectMapper.readValue(responseBody, UserPositionDTOImpl.class);
        Assert.assertEquals(userId, userPositionDTO.getUserId().intValue());
        Assert.assertEquals(points, userPositionDTO.getScore().intValue());
        Assert.assertEquals(points, userPositionDTO.getScore().intValue());
    }

    @And("the score was saved in the database")
    public void theScoreWasSavedInTheDatabase() {
        verify(scoreRepository, times(1)).save(any(UserScore.class));
    }
}
