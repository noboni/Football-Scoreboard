package com.example.footballScoreboard;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreBoardImpl implements ScoreBoard {
    private List<Match> matches = new ArrayList<>();
    private int id = 1;

    public ScoreBoardImpl() {
        matches = new ArrayList<>();

    }

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match == null) {
            match = new Match(id++, homeTeam, awayTeam, 0, 0, System.currentTimeMillis());
            matches.add(match);
        }

    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match != null) {
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
        }
    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match != null) {
            matches.remove(match);
        }
    }

    @Override
    public List<Match> getMatchesInProgress() {
        return matches.stream()
                .sorted(Comparator.comparing(Match::getTotalScore)
                        .thenComparing(Match::getStartTime).reversed()
                        )
                .collect(Collectors.toList());

    }

    private Match findMatch(String homeTeam, String awayTeam) {
        for (Match match : matches) {
            if (match.getHomeTeam().equalsIgnoreCase(homeTeam)
                    && match.getAwayTeam().equalsIgnoreCase(awayTeam)) {
                return match;
            }
        }
        return null;
    }
}
