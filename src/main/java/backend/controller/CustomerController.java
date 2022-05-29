package backend.controller;

import backend.model.Customer;
import backend.service.CustomerService;
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
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    private static Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        logger.info("Getting all users...");
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getUser(@PathVariable Long id) {
        logger.info("Getting customer with ID {}...", id);
        return service.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody Customer user) throws URISyntaxException {
        logger.info("Creating customer {}...", user.getUsername());
        Customer createdUser = service.createCustomer(user);
        if (createdUser == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid data!");
        else return ResponseEntity.created(new URI("/customer/" + createdUser.getUserID())).body(createdUser);
    }

}
