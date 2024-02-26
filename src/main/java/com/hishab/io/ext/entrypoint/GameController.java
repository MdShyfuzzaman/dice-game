package com.hishab.io.ext.entrypoint;

import com.hishab.io.ext.model.Player;
import com.hishab.io.ext.service.DiceRollAndPlayService;
import com.hishab.io.ext.service.PlayerService;
import com.hishab.io.ext.support.DiceGameConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Game controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {

    /**
     * The Dice service.
     */
    private final DiceRollAndPlayService diceRollAndPlayService;

    /**
     * The Player service.
     */
    private final PlayerService playerService;

    /**
     * New game response entity.
     *
     * @return the response entity
     */
    @GetMapping("/start")
    public ResponseEntity<String> newGame() {
        playerService.resetPlayer();
        return ResponseEntity.ok("Now Create Players!!!");
    }


    /**
     * Create player response entity.
     *
     * @param player the player
     * @return the response entity
     */
    @PostMapping(value = "/create/player", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPlayer(@RequestBody @Valid Player player) {
        if (playerService.getTotalActivePlayer() >= DiceGameConstant.MAX_PLAYER_ALLOWED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum players limit reached");
        }
        playerService.createPlayer(player);
        return ResponseEntity.ok("Player created successfully!!!");
    }

    /**
     * Start game response entity.
     *
     * @return the response entity
     */
    @PostMapping("/play/game")
    public ResponseEntity<String> startGame() {
        if (playerService.getTotalActivePlayer() < DiceGameConstant.MIN_PLAYER_REQUIRED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Minimum 2 players required");
        }
        // Game logic
        diceRollAndPlayService.rollDice(playerService.getActivePlayer());

        return ResponseEntity.ok("Game Finish!!! Attendant players are : " + playerService.getActivePlayer());
    }

    /**
     * Gets scores.
     *
     * @return the scores
     */
    @GetMapping("/scores")
    public ResponseEntity<Map<String, Integer>> getScores() {
        Map<String, Integer> scores = getPlayersScores();
        return ResponseEntity.ok(scores);
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    @GetMapping("/winner")
    public ResponseEntity<String> getWinner() {
        int max = Collections.max(getPlayersScores().values());
        Optional<String> firstKey = getPlayersScores().entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .findFirst();
        return firstKey.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok("No Scorer Found!!!"));
    }

    /**
     * Gets players scores.
     *
     * @return the players scores
     */
    private Map<String, Integer> getPlayersScores() {
        Map<String, Integer> scores = new HashMap<>();
        for (Player player : playerService.getActivePlayer().values()) {
            scores.put(player.getName(), player.getTotalScore());
        }
        return scores;
    }
}