package service;

import backend.model.Customer;
import backend.repository.CartRepository;
import backend.repository.CustomerRepository;
import backend.service.CustomerService;
import backend.service.utils.Encrypting;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private final CustomerService customerService = new CustomerService();

    @Test
    public void createInvalidUserTest() {
        Customer invalidCustomer = new Customer("Calea Turzii", "Mihai", "Popa", "abcd01",
                "0758697236", "mihaipopa01", "mihaipopa");
        Mockito.doReturn(Optional.of(invalidCustomer)).when(customerRepository).save(invalidCustomer);
        Customer createdCustomer = customerService.createCustomer(invalidCustomer);
        Assertions.assertNull(createdCustomer);
    }

    @Test
    public void createValidUserTest() {
        Customer validCustomer = new Customer("Calea Turzii", "Mihail", "Pop", "abcd01",
                "0758697236", "mihaipopa01", "mihaipopa@gmail.cpm");
        Mockito.doReturn(validCustomer).when(customerRepository).save(validCustomer);
        Customer createdCustomer = customerService.createCustomer(validCustomer);
        Assertions.assertEquals(validCustomer.getFirstName(), createdCustomer.getFirstName());
    }

    @Test
    public void getValidUserTest() {
        Long id = 6L;
        Customer expectedCustomer = new Customer("Strada George Barițiu 26-28",
                "Alex", "Moraru", Encrypting.encrypt("alexmoraru25"),
                "0758632390", "alexmoraru25", "alexmoraru25@yahoo.com");
        Mockito.doReturn(Optional.of(expectedCustomer)).when(customerRepository).findById(id);
        Customer actualCustomer = customerService.getCustomerById(id);
        Assertions.assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
    }

    @Test
    public void getInvalidUserTest() {
        Long id = 2L;
        Mockito.doReturn(Optional.empty()).when(customerRepository).findById(id);
        Customer actualCustomer = null;
        try {
            actualCustomer = customerService.getCustomerById(id);
        } catch (RuntimeException e) {
            System.out.println("Failed get customer by ID operation!");
        }
        Assertions.assertNull(actualCustomer);
    }

}
