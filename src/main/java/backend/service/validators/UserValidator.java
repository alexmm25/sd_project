package backend.service.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Composite DP

public class UserValidator implements Validator {

    private List<Validator> validators = new ArrayList<>(Arrays.asList(
            new CustomerValidator(),
            new SponsorValidator()));

    public CustomerValidator getCustomerValidator() {
        return (CustomerValidator) validators.stream().filter(validator -> validator.getClass().
                equals(CustomerValidator.class)).collect(Collectors.toList()).get(0);
    }

    public SponsorValidator getSponsorValidator() {
        return (SponsorValidator) validators.stream().filter(validator -> validator.getClass().
                equals(SponsorValidator.class)).collect(Collectors.toList()).get(0);
    }


    @Override
    public boolean validateUsername(String username) {
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        return true;
    }

    @Override
    public boolean validateEmail(String email) {
        return true;
    }

    @Override
    public boolean validateName(String name) {
        return true;
    }
}
