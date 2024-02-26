package com.hishab.io.ext.service.impl;

import com.hishab.io.ext.exception.DiceGameAPIException;
import com.hishab.io.ext.model.Player;
import com.hishab.io.ext.repository.PlayerRepository;
import com.hishab.io.ext.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * The type Player service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerServiceImpl implements PlayerService {
    /**
     * The Player repository.
     */
    private final PlayerRepository playerRepository;

    /**
     * Create player.
     *
     * @param player the player
     */
    @Override
    public void createPlayer(Player player) {
        try {
            playerRepository.savePlayer(player);
        } catch (Exception e) {
            throw new DiceGameAPIException("Error during player creation");
        }
    }

    /**
     * Reset player.
     */
    @Override
    public void resetPlayer() {
        try {
            playerRepository.resetPlayer();
        } catch (Exception e) {
            throw new DiceGameAPIException("Board reset problem");
        }
    }

    /**
     * Gets total active player.
     *
     * @return the total active player
     */
    @Override
    public int getTotalActivePlayer() {
        try {
            return playerRepository.getTotalActivePlayer();
        } catch (Exception e) {
            throw new DiceGameAPIException("Does not retrieve total active players");
        }
    }

    /**
     * Gets active player.
     *
     * @return the active player
     */
    @Override
    public Map<String, Player> getActivePlayer() {
        try {
            return playerRepository.getActivePlayer();
        } catch (Exception e) {
            throw new DiceGameAPIException("Does not retrieve active players");
        }
    }

}