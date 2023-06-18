package com.example.footballScoreboard;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FootballScoreboardApplicationTests {

	@Test
	public void testStartMatch() {

		ScoreBoard scoreBoard = new ScoreBoardImpl();

		// Define the expected match details
		String homeTeam = "Mexico";
		String awayTeam = "Canada";

		scoreBoard.startMatch(homeTeam,awayTeam);

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
		scoreBoard.startMatch("Mexico","Canada");
		// Update the score
		scoreBoard.updateScore("Mexico","Canada", 0, 5);

		// Assert that the score is updated
		List<Match> matches = scoreBoard.getMatchesInProgress();
		assertEquals(1, matches.size());
		assertEquals(0, matches.get(0).getHomeScore());
		assertEquals(5, matches.get(0).getAwayScore());
	}

	@Test
	void contextLoads() {
	}

}
