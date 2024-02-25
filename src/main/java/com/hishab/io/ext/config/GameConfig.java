package com.hishab.io.ext.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Game config.
 */
@Component
@Getter
public class GameConfig {

    /**
     * The Start score.
     */
    @Value("${dice.game.start-score}")
    public int startScore;

    /**
     * The Penalty score.
     */
    @Value("${dice.game.penalty-score}")
    public int penaltyScore;

    /**
     * The Winning score.
     */
    @Value("${dice.game.winning-score}")
    public int winningScore;
}