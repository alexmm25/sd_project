package backend.service;

import backend.model.Stadium;
import backend.repository.StadiumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumService {

    private static Logger logger = LogManager.getLogger(StadiumService.class);

    @Autowired
    private StadiumRepository stadiumRepository;

    public List<Stadium> getStadiums() {
        logger.info("Got stadium info!");
        return stadiumRepository.findAll();
    }
}
