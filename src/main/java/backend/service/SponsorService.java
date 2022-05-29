package backend.service;

import backend.model.Sponsor;
import backend.repository.SponsorRepository;
import backend.repository.UserRepository;
import backend.service.utils.Encrypting;
import backend.service.validators.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SponsorService {

    private static Logger logger = LogManager.getLogger(SponsorService.class);

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param sponsor the sponsor entity with data inserted in the front end side
     * @return true if an existing account contains that data, false otherwise
     */
    public Sponsor logIn(Sponsor sponsor) {
        Sponsor existentSponsor = null;
        String existentPassword = userRepository.findPasswordByUsername(sponsor.getUsername());
        if (existentPassword != null)
            if (Encrypting.check(sponsor.getPassword(), existentPassword)) {
                List<Sponsor> sponsors = sponsorRepository.findAll().stream().filter(u -> u.getUsername().
                        equals(sponsor.getUsername())).collect(Collectors.toList());
                if(!sponsors.isEmpty())
                    existentSponsor = sponsors.get(0);
            }
        if(existentSponsor != null)
            logger.info("Sponsor {} logged in successfully!", sponsor.getUsername());
        else logger.warn("Sponsor {} log in failed!", sponsor.getUsername());
        return existentSponsor;
    }

    /**
     *
     * @return a list of all sponsors
     */
    public List<Sponsor> getAllSponsors() {
        logger.info("Got sponsors successfully!");
        return sponsorRepository.findAll();
    }

    /**
     *
     * @param id the searched sponsor's id
     * @return the found sponsor entity; an exception is thrown if none is found
     */
    public Sponsor getSponsorById(Long id) {
        Sponsor searchedSponsor = sponsorRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Sponsor with username {} found successfully!", searchedSponsor.getUsername());
        return searchedSponsor;
    }

    /**
     *
     * @param username the username to identify the sponsor
     * @return the id of the searched sponsor
     */
    public Long getSponsorByUsername(String username) {
        Long searchedID = sponsorRepository.findIDbyUsername(username);
        if (searchedID == null)
            logger.warn("Sponsor with username {} not found!", username);
        else logger.info("Sponsor with username {} found successfully!", username);
        return searchedID;
    }

    /**
     *
     * @param sponsor the sponsor with parameters inserted by the user on the web page
     * @return the created sponsor or null if the data is not valid
     */
    public Sponsor createSponsor(Sponsor sponsor) {
        if (new UserValidator().getSponsorValidator().validateSponsor(sponsor)) {
            logger.info("Sponsor {} was successfully created!", sponsor.getName());
            sponsor.setPassword(Encrypting.encrypt(sponsor.getPassword()));
            return sponsorRepository.save(sponsor);
        }
        else{
            logger.info("Sponsor {} creation failed. Invalid data!", sponsor.getName());
            return null;
        }
    }

    /**
     *
     * @param name the searched name of the sponsor
     * @return the searched sponsor's ID
     */
    public Long getSponsorIDByName(String name) {
        return sponsorRepository.findIDByName(name);
    }

}