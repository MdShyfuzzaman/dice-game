package com.hishab.io.ext.repository;

import com.hishab.io.ext.model.Player;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Player repository.
 */
@Repository
public class PlayerRepository {
    /**
     * The Players.
     */
    private final Map<String, Player> players = new HashMap<>();

    /**
     * Save player.
     *
     * @param player the player
     */
    public Player savePlayer(Player player) {
        players.put(player.getName(), player);
        return player;
    }

    /**
     * Gets active player.
     *
     * @return the active player
     */
    public int getTotalActivePlayer() {
        return players.size();
    }

    /**
     * Gets active player.
     *
     * @return the active player
     */
    public Map<String, Player> getActivePlayer() {
        return players;
    }

    /**
     * Reset player.
     */
    public void resetPlayer() {
        players.clear();
    }
}
