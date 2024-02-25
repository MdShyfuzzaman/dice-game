package com.hishab.io.ext.service;

import com.hishab.io.ext.model.Player;

import java.util.Map;

/**
 * The interface Dice service.
 */
public interface DiceRollAndPlayService {
    /**
     * Roll dice int.
     */
    void rollDice(Map<String, Player> players );
}
