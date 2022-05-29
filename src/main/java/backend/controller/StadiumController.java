package backend.controller;

import backend.model.CurrentStadium;
import backend.model.Stadium;
import backend.service.StadiumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stadium")
@CrossOrigin
public class StadiumController {

    private static Logger logger = LogManager.getLogger(StadiumController.class);

    @Autowired
    private StadiumService stadiumService;

    CurrentStadium currentStadium = CurrentStadium.getSTADIUM();

    @GetMapping
    public CurrentStadium getCurrentStadium() {
        logger.info("Getting stadium info...");
        Stadium stadium = stadiumService.getStadiums().get(0);
        currentStadium.setCapacity(stadium.getCapacity());
        currentStadium.setName(stadium.getName());
        currentStadium.setAvailableTickets(stadium.getAvailableTickets());
        currentStadium.setInauguration(stadium.getInauguration());
        currentStadium.setExtraDetails(stadium.getExtraDetails());
        return currentStadium;
    }
}
