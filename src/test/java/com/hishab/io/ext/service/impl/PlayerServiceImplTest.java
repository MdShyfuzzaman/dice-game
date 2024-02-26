package com.hishab.io.ext.service.impl;

import com.hishab.io.ext.model.Player;
import com.hishab.io.ext.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void createPlayerSuccessTest() {
        when(playerRepository.savePlayer(any())).thenReturn(Player.builder().age(35)
                .name("Mr. John").build());
        Player player = playerService.createPlayer(Player
                .builder()
                .age(35)
                .name("Mr. John")
                .build());

        Assertions.assertNotNull(player);
        Assertions.assertEquals("Mr. John", player.getName());
        Assertions.assertEquals(35, player.getAge());
    }

    @Test
    void resetPlayerSuccessTest() {
        playerService.resetPlayer();
        Assertions.assertEquals(0, playerRepository.getTotalActivePlayer());
    }
}