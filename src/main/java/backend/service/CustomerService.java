package backend.service;

import backend.model.Cart;
import backend.model.Customer;
import backend.repository.CustomerRepository;
import backend.repository.UserRepository;
import backend.service.utils.Encrypting;
import backend.service.validators.UserValidator;
import backend.repository.CartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static Logger logger = LogManager.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return a list of all the users from the database
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     *
     * @param id the identifier by which the user is searched
     * @return the user having the given id; an exception is thrown if the user wasn't found
     */
    public Customer getCustomerById(Long id) {
        Customer searchedCustomer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Customer with ID {} found successfully!", id);
        return searchedCustomer;
    }

    /**
     *
     * @param customer the user with data passed from the front end side
     * @return the created user, or null if the user's data is not valid
     */
    public Customer createCustomer(Customer customer) {
        if (new UserValidator().getCustomerValidator().validateCustomer(customer)) {
            customer.setPassword(Encrypting.encrypt(customer.getPassword()));
            Cart cart = new Cart();
            Customer savedCustomer = customerRepository.save(customer);
            cart.setCartID(savedCustomer.getUserID());
            Cart savedCart = cartRepository.save(cart);
            savedCustomer.setCart(savedCart);
            customerRepository.updateUserCart(savedCustomer.getUserID(), savedCart);
            logger.info("Customer {} created successfully!", customer.getUsername());
            return savedCustomer;
        }
        else {
            logger.warn("Customer insertion failed. Invalid user data!");
            return null;
        }
    }

    /**
     *
     * @param customer the user containing only 2 fields: username and password
     * @return the user entity having the desired credentials from the database, or null if the credentials are wrong
     */
    public Customer logIn(Customer customer) {
        Customer newCustomer = null;
        String existentPassword = userRepository.findPasswordByUsername(customer.getUsername());
        if(existentPassword != null)
            if (Encrypting.check(customer.getPassword(), existentPassword)) {
                List<Customer> customers = customerRepository.findAll().stream().filter(u -> u.getUsername().
                        equals(customer.getUsername())).collect(Collectors.toList());
                if (!customers.isEmpty())
                    newCustomer = customers.get(0);
            }
        if (newCustomer == null) logger.warn("Log in of customer {} failed!", customer.getUsername());
        else logger.info("Customer {} logged in successfully!", customer.getUsername());
        return newCustomer;
    }

}
