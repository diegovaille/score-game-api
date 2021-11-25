package com.demo.game.score.api.steps;

import com.demo.game.score.api.controller.request.UserScoreRequest;
import com.demo.game.score.api.controller.response.HighScoreDTO;
import com.demo.game.score.api.controller.response.IUserPosition;
import com.demo.game.score.api.controller.response.UserPositionDTO;
import com.demo.game.score.api.domain.UserScore;
import com.demo.game.score.api.repository.ScoreH2Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ScoreH2Repository scoreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions resultActions;

    @Then("the client receives status code of {int}")
    public void thenTheClientReceivesStatusCodeOf(int statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
    }

    @When("client sends score with userId {int} and points {int}")
    public void clientSendsScoreWithUserIdAndPoints(int userId, int points) throws Exception {

        var request = new UserScoreRequest(userId, points);
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/h2/score")
                                                .content(objectMapper.writeValueAsString(request))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }

    @When("client sends score with userId NULL and points {int}")
    public void clientSendsScoreWithUserIdNULLAndPoints(int points) throws Exception {
        var request = new UserScoreRequest(null, points);
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/h2/score")
                                                .content(objectMapper.writeValueAsString(request))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }

    @When("client sends a GET with userId {int}")
    public void clientsSendsAGETWithUserId(Integer userId) throws Exception {
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .get(String.join("/", "/h2", userId.toString(), "position"))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));

    }

    @And("the response has a body object with userId {int}, points {int} and position {int}")
    public void theResponseHasABodyObjectWithUserIdPointsAndPosition(int userId, int points, int position) throws IOException {
        String responseBody = resultActions.andReturn()
                                           .getResponse()
                                           .getContentAsString();

        Assert.assertNotNull(responseBody);

        UserPositionDTO userPositionDTO = objectMapper.readValue(responseBody, UserPositionDTO.class);
        Assert.assertEquals(userId, userPositionDTO.getUserId()
                                                   .intValue());
        Assert.assertEquals(points, userPositionDTO.getScore()
                                                   .intValue());
        Assert.assertEquals(position, userPositionDTO.getPosition()
                                                   .intValue());
    }

    @And("the score was saved in the database")
    public void theScoreWasSavedInTheDatabase() {
        verify(scoreRepository, times(1)).save(any(UserScore.class));
    }

    @Given("there are these scores already in the database:$")
    @Transactional
    public void thereIsAHighScoreListAlreadyCreatedAsBelow(DataTable dataTable) {
        List<Map<String, Integer>> list = dataTable.asMaps(String.class, Integer.class);
        List<IUserPosition> userPositionDTOList = new ArrayList<>();
        for (Map<String, Integer> map : list) {
//            UserPositionDTO userPositionDTO = new UserPositionDTO(map.get("userId"), map.get("score"), map.get("position"));
//            userPositionDTOList.add(userPositionDTO);
            UserScore userScore = new UserScore(map.get("userId"), map.get("score"));
            scoreRepository.save(userScore);
            //            when(scoreRepository.getUserScore(userPositionDTO.getUserId())).thenReturn(Optional.of(userPositionDTO));
        }

        //        when(scoreRepository.getHighScoreList()).thenReturn(userPositionDTOList);
    }

    @And("the response has an emtpy body object")
    public void theResponseHasAnEmtpyBodyObject() throws UnsupportedEncodingException {

        String responseBody = resultActions.andReturn()
                                           .getResponse()
                                           .getContentAsString();

        Assert.assertNotNull(responseBody);

        Assert.assertEquals("{}", responseBody);
    }

    @When("client sends a GET to highscorelist")
    public void clientSendsAGETToHighscorelist() throws Exception {
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .get("/h2/highscorelist")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }

    @And("the response body has a highscorelist")
    public void theResponseBodyHighscorelistIsAsFollows() throws IOException {
        String responseBody = resultActions.andReturn()
                                           .getResponse()
                                           .getContentAsString();
        Assert.assertNotNull(responseBody);

        TypeReference<HighScoreDTO<UserPositionDTO>> reference = new TypeReference<>() {};
        var response = objectMapper.readValue(responseBody, reference);

        Assert.assertNotNull(response);
    }

    @When("client sends scores with userId {int} and points NULL")
    public void clientSendsScoresWithUserIdAndPointsNULL(int userId) throws Exception {
        var request = new UserScoreRequest(userId, null);
        resultActions = mockMvc.perform(MockMvcRequestBuilders
                                                .post("/h2/score")
                                                .content(objectMapper.writeValueAsString(request))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .accept(MediaType.APPLICATION_JSON));
    }
}
