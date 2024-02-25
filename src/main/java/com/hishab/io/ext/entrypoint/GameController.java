package com.hishab.io.ext.entrypoint;

import com.hishab.io.ext.model.Player;
import com.hishab.io.ext.service.DiceRollAndPlayService;
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
import java.util.HashMap;
import java.util.Map;

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
     * The Players.
     */
    private final Map<String, Player> players = new HashMap<>();

    /**
     * Create player response entity.
     *
     * @param player the player
     * @return the response entity
     */
    @PostMapping("/players")
    public ResponseEntity<String> createPlayer(@RequestBody @Valid Player player) {
        if (players.size() >= DiceGameConstant.MAX_PLAYER_ALLOWED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum players limit reached");
        }
        players.put(player.getName(), player);
        return ResponseEntity.ok("Player created successfully");
    }

    /**
     * Start game response entity.
     *
     * @return the response entity
     */
    @PostMapping("/start")
    public ResponseEntity<String> startGame() {
        if (players.size() < DiceGameConstant.MIN_PLAYER_REQUIRED) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Minimum 2 players required");
        }
        // Game logic
        diceRollAndPlayService.rollDice(players);

        return ResponseEntity.ok("Game started");
    }

    /**
     * Gets scores.
     *
     * @return the scores
     */
    @GetMapping("/scores")
    public ResponseEntity<Map<String, Integer>> getScores() {
        Map<String, Integer> scores = new HashMap<>();
        for (Player player : players.values()) {
            scores.put(player.getName(), player.getTotalScore());
        }
        return ResponseEntity.ok(scores);
    }
}