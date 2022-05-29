package backend.controller;

import backend.model.Player;
import backend.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {

    private static Logger logger = LogManager.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity createPlayer(@RequestBody Player player) {
        logger.info("Creating player {}...", player.getName());
        Player createdPlayer = playerService.createPlayer(player);
        if (createdPlayer == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid data!");
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        logger.info("Getting all players...");
        return playerService.getAllPlayers();
    }

}
