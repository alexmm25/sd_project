package service;

import backend.model.Player;
import backend.model.playerDetails.Position;
import backend.model.playerDetails.PreferredFoot;
import backend.repository.PlayerRepository;
import backend.service.PlayerService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService = new PlayerService();

    @Test
    public void createValidPlayer() {
        Player validPlayer = new Player("Good Player", 7, 25, "French", PreferredFoot.LEFT,
                Position.ATTACKING_MIDFIELDER, 123, 56);
        Mockito.doReturn(validPlayer).when(playerRepository).save(validPlayer);
        Player createdPlayer = playerService.createPlayer(validPlayer);
        Assertions.assertEquals(validPlayer.getName(), createdPlayer.getName());
    }

    @Test
    public void createInvalidPlayer() {
        Player invalidPlayer = new Player("Bad Player", 7, 5, "French", PreferredFoot.LEFT,
                Position.ATTACKING_MIDFIELDER, 123, 56);
        Mockito.doReturn(Optional.of(invalidPlayer)).when(playerRepository).save(invalidPlayer);
        Assertions.assertNull(playerService.createPlayer(invalidPlayer));
    }


}
