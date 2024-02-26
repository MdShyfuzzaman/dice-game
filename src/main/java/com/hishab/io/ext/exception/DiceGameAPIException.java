package com.hishab.io.ext.exception;

import lombok.Getter;

/**
 * The type Dice game api exception.
 */
@Getter
public class DiceGameAPIException extends RuntimeException {
    /**
     * The Error message.
     */
    private final String errorMessage;

    /**
     * Instantiates a new Dice game api exception.
     *
     * @param errorMessage the error message
     */
    public DiceGameAPIException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
