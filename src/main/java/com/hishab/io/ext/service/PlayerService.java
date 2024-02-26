package com.hishab.io.ext.service;

import com.hishab.io.ext.model.Player;

import java.util.Map;


/**
 * The interface Player service.
 */
public interface PlayerService {

    /**
     * Create player.
     *
     * @param player the player
     */
    Player createPlayer(Player player);

    /**
     * Reset player.
     */
    void resetPlayer();

    /**
     * Gets total active player.
     *
     * @return the total active player
     */
    int getTotalActivePlayer();

    /**
     * Gets active player.
     *
     * @return the active player
     */
    Map<String, Player> getActivePlayer();
}
