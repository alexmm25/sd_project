package backend.controller;

import backend.model.Sponsor;
import backend.service.SponsorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/sponsor")
@CrossOrigin
public class SponsorController {

    private static Logger logger = LogManager.getLogger(SponsorController.class.getName());

    @Autowired
    private SponsorService service;

    public SponsorController(SponsorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sponsor> getSponsors() {
        logger.info("Getting all sponsors...");
        return service.getAllSponsors();
    }

    @GetMapping("/{id}")
    public Sponsor getSponsor(@PathVariable Long id) {
        logger.info("Getting sponsor by ID...");
        return service.getSponsorById(id);
    }

    @GetMapping("/login/{username}")
    public Long getSponsorByUsername(@PathVariable String username) {
        logger.info("Getting sponsor by username...");
        return service.getSponsorByUsername(username);
    }

    @GetMapping("/N/{name}")
    public Long getSponsorIDByName(@PathVariable String name) {
        logger.info("Getting sponsor ID by name...");
        return service.getSponsorIDByName(name);
    }

    @PostMapping
    public ResponseEntity createSponsor(@RequestBody Sponsor user) throws URISyntaxException {
        logger.info("Creating customer {}...", user.getUsername());
        Sponsor createdUser = service.createSponsor(user);
        if (createdUser == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid data!");
        else return ResponseEntity.created(new URI("/sponsor/" + createdUser.getUserID())).body(createdUser);
    }

}
