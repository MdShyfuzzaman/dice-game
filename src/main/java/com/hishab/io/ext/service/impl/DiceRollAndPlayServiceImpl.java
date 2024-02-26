package com.hishab.io.ext.service.impl;

import com.hishab.io.ext.config.GameConfig;
import com.hishab.io.ext.exception.DiceGameAPIException;
import com.hishab.io.ext.model.Player;
import com.hishab.io.ext.service.DiceRollAndPlayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The type Dice service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DiceRollAndPlayServiceImpl implements DiceRollAndPlayService {
    /**
     * The Rest template.
     */
    private final RestTemplate restTemplate;

    /**
     * The Game config.
     */
    private final GameConfig gameConfig;
    /**
     * The Random.
     */
    private final Random random = new Random();
    /**
     * The Inner url.
     */
    @Value("${role.dice.api.url}")
    String innerUrl;

    /**
     * Instantiates a new Dice service.
     *
     * @param restTemplateBuilder the rest template builder
     * @param gameConfig          the game config
     */
    public DiceRollAndPlayServiceImpl(RestTemplateBuilder restTemplateBuilder, GameConfig gameConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.gameConfig = gameConfig;
    }


    /**
     * Roll dice int.
     *
     * @param players the players
     */
    @Override
    @Async
    public void rollDice(Map<String, Player> players) {
        rollAndPlay(players);
    }

    /**
     * Roll and play.
     *
     * @param playerMap the player map
     */
    private void rollAndPlay(Map<String, Player> playerMap) {
        List<Player> players = new ArrayList<>(playerMap.values());
        try {
            int index = 0;
            int maxScore = 0;
            log.info("Stat Playing game");
            while (true) {
                int currentScore = getDiceRollingValue();
                Player player = players.get(index);
                if (index == 1)
                    log.info("Index : [{}] , current score:[{}]", index, currentScore);
                if (maxScore >= gameConfig.getWinningScore()) {
                    break;
                }
                if (currentScore == gameConfig.getPenaltyScore()) {
                    if (!player.isStartRolling()) {
                        player.setTotalScore(Math.max(0, player.getTotalScore() - gameConfig.getPenaltyScore()));
                    }
                    log.info("Player : [{}] get penalty, current score:[{}]", index, player.getTotalScore());
                    player.setStartRolling(false);
                    index = nextIndex(index, players.size());
                } else if (currentScore == gameConfig.getStartScore() && !player.isStartRolling()) {
                    player.setStartRolling(true);
                    log.info("Player : [{}] start rolling, current score:[{}]", index, player.getTotalScore());
                } else if (currentScore == gameConfig.getStartScore()) {
                    player.setTotalScore(player.getTotalScore() + currentScore);
                    log.info("Player : [{}] hit 6, current score:[{}]", index, player.getTotalScore());
                } else {
                    player.setTotalScore(player.getTotalScore() + currentScore);
                    log.info(" Player : [{}] , current score:[{}]", index, player.getTotalScore());
                    player.setStartRolling(false);
                    index = nextIndex(index, players.size());
                }
                maxScore = Math.max(maxScore, player.getTotalScore());
            }
            log.info("End Playing game");
        } catch (Exception e) {
            log.error("Exception while paling game", e);
            throw new DiceGameAPIException("Exception while paling game");
        }
    }

    /**
     * Next index int.
     *
     * @param index      the index
     * @param upperBound the upper bound
     * @return the int
     */
    private static int nextIndex(int index, int upperBound) {
        return (++index) % upperBound;
    }

    /**
     * Gets dice rolling value.
     *
     * @return the dice rolling value
     */
    private int getDiceRollingValue() {
        try {
            ResponseEntity<Integer> response = restTemplate.getForEntity(innerUrl, Integer.class);
            if (response.getBody() == null) { // If inner API not found then create random dice value
                // Generate a random number between 1 and 6 (inclusive)
                return random.nextInt(6) + 1;
            }
            return response.getBody();
        } catch (Exception e) {
            log.error("Inner API Exception : {}", e.getMessage());
            return random.nextInt(6) + 1;// If inner API not found then generate random dice value
        }
    }

}