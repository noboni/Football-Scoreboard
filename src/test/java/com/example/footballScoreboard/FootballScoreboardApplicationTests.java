package com.example.footballScoreboard;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FootballScoreboardApplicationTests {

	@Autowired
	ScoreBoard scoreBoard;

	@Test
	public void testStartMatch() {

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
	void contextLoads() {
	}

}
