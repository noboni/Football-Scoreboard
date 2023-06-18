package com.example.footballScoreboard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FootballScoreboardApplicationTests {

    @Test
    public void testStartMatch() {

        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // Define the expected match details
        String homeTeam = "Mexico";
        String awayTeam = "Canada";

        scoreBoard.startMatch(homeTeam, awayTeam);

        List<Match> matches = scoreBoard.getMatchesInProgress();
        Match match = matches.get(0);

        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testUpdateScore() {

        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // start a match
        scoreBoard.startMatch("Mexico", "Canada");
        // Update the score
        scoreBoard.updateScore("Mexico", "Canada", 0, 5);

        // Assert that the score is updated
        List<Match> matches = scoreBoard.getMatchesInProgress();
        assertEquals(1, matches.size());
        assertEquals(0, matches.get(0).getHomeScore());
        assertEquals(5, matches.get(0).getAwayScore());
    }

    @Test
    public void testFinishMatch() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // Start a new match
        scoreBoard.startMatch("Mexico", "Canada");

        // Finish the match
        scoreBoard.finishMatch("Mexico", "Canada");

        // Assert that the match is removed from the scoreboard
        List<Match> matches = scoreBoard.getMatchesInProgress();
        assertEquals(0, matches.size());
    }

    @Test
    public void testFinishNonExistentMatch() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // Start a new match
        scoreBoard.startMatch("Mexico", "Canada");

        // Finish a match that doesn't exist
        scoreBoard.finishMatch("Spain", "Brazil");

        // Assert that the match is not removed from the scoreboard
        List<Match> matches = scoreBoard.getMatchesInProgress();
        assertEquals(1, matches.size());
    }

    @Test
    public void testGetMatchesInProgress_EmptyList() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        List<Match> matches = scoreBoard.getMatchesInProgress();

        assertTrue(matches.isEmpty());
    }

    @Test
    public void testGetMatchesInProgress_SingleMatch() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        String homeTeam = "Mexico";
        String awayTeam = "Canada";
        scoreBoard.startMatch(homeTeam, awayTeam);

        List<Match> matches = scoreBoard.getMatchesInProgress();

        assertEquals(1, matches.size());
        Match match = matches.get(0);
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testGetMatchesInProgress_DifferentTotalScore() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // Start multiple matches
        scoreBoard.startMatch("Uruguay", "Italy");
        scoreBoard.startMatch("Argentina", "Australia");
        scoreBoard.startMatch("Germany", "France");

        // Update the scores of the matches
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);
        scoreBoard.updateScore("Germany", "France", 3, 2);

        // Get the matches in progress
        List<Match> matches = scoreBoard.getMatchesInProgress();

        // Assert the order of the matches based on total score and start time
        assertEquals("Uruguay", matches.get(0).getHomeTeam());
        assertEquals("Italy", matches.get(0).getAwayTeam());
        assertEquals("Germany", matches.get(1).getHomeTeam());
        assertEquals("France", matches.get(1).getAwayTeam());
        assertEquals("Argentina", matches.get(2).getHomeTeam());
        assertEquals("Australia", matches.get(2).getAwayTeam());
    }

    @Test
    public void testGetMatchesInProgress_SameTotalScoreDifferentStartTime() {
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        // Start multiple matches
        scoreBoard.startMatch("Uruguay", "Italy");
        scoreBoard.startMatch("Argentina", "Australia");
        scoreBoard.startMatch("Germany", "France");

        // Update the scores of the matches
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);
        scoreBoard.updateScore("Germany", "France", 2, 2);

        // Get the matches in progress
        List<Match> matches = scoreBoard.getMatchesInProgress();

        // Assert the order of the matches based on total score and start time
        assertEquals("Uruguay", matches.get(0).getHomeTeam());
        assertEquals("Italy", matches.get(0).getAwayTeam());
        assertEquals("Argentina", matches.get(1).getHomeTeam());
        assertEquals("Australia", matches.get(1).getAwayTeam());
        assertEquals("Germany", matches.get(2).getHomeTeam());
        assertEquals("France", matches.get(2).getAwayTeam());

    }


        @Test
    void contextLoads() {
    }

}
