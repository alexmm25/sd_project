package backend.service;

import backend.repository.PlayerRepository;
import backend.model.Player;
import backend.service.validators.PlayerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private static Logger logger = LogManager.getLogger(PlayerService.class);

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        logger.info("Got all players!");
        return playerRepository.findAll();
    }

    /**
     *
     * @param player the sponsor with parameters inserted by the user on the web page
     * @return the created sponsor or null if the data is not valid
     */
    public Player createPlayer(Player player) {
        if (new PlayerValidator().validatePlayer(player)) {
            logger.info("Player {} was successfully created!", player.getName());
            return playerRepository.save(player);
        }
        else{
            logger.warn("Player {} creation failed. Invalid data!", player.getName());
            return null;
        }
    }
}
