package com.example.footballScoreboard;

import java.util.List;

public interface ScoreBoard {
    void startMatch(String homeTeam, String awayTeam);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    void finishMatch(String homeTeam, String awayTeam);
    List<Match> getMatchesInProgress();

}
