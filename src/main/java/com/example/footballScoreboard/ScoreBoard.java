package com.example.footballScoreboard;

import java.util.List;

public interface ScoreBoard {
    void startMatch(String homeTeam, String awayTeam);
    void updateScore(int id, int homeScore, int awayScore);
    void finishMatch(int id);
    List<Match> getMatchesInProgress();

}
