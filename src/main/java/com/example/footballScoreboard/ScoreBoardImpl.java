package com.example.footballScoreboard;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreBoardImpl implements ScoreBoard{
    private final List<Match> matches = new ArrayList<>();
    private int id = 1;

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(id++, homeTeam, awayTeam,0 ,0, System.currentTimeMillis() );
        matches.add(match);

    }

    @Override
    public void updateScore(int id, int homeScore, int awayScore) {

    }

    @Override
    public void finishMatch(int id) {

    }

    @Override
    public List<Match> getMatchesInProgress() {
        return matches;
    }
}
