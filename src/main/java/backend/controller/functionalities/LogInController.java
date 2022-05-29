package backend.controller.functionalities;

import backend.model.Sponsor;
import backend.service.SponsorService;
import backend.model.Customer;
import backend.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LogInController {
    private static Logger logger = LogManager.getLogger(LogInController.class.getName());

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity logInSponsor(@RequestBody Customer user) {
        logger.info("User {} logging in...", user.getUsername());
        Customer existentCustomer = customerService.logIn(new Customer(user.getUsername(), user.getPassword()));
        Sponsor existentSponsor = sponsorService.logIn(new Sponsor(user.getUsername(), user.getPassword()));
        if (user.getUsername().equals("admin") && user.getPassword().equals("admin"))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(0);
        else if (existentSponsor != null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body((existentSponsor.getUserID() + "-sponsor"));
        else if (existentCustomer != null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body((existentCustomer.getUserID() + "-customer"));
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account doesn't exist!");
    }
}
