package com.hishab.io.ext.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * The type Player.
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
    /**
     * The Name.
     */
    @NonNull
    @NotBlank(message = "Should be player name")
    @NotEmpty(message = "Should be player name")
    @JsonProperty("name")
    private String name;
    /**
     * The Age.
     */
    @NonNull
    @Min(value = 5, message = "Player age should greater than 5")
    @JsonProperty("age")
    private Integer age;
    /**
     * The Total score.
     */
    @JsonProperty("totalScore")
    private int totalScore;
    /**
     * The Start rolling.
     */
    @JsonProperty("startRolling")
    private boolean startRolling;
}
