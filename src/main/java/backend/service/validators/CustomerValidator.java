package backend.service.validators;

import backend.model.Customer;

import java.util.regex.Pattern;

public class CustomerValidator implements Validator {

    /**
     *
     * @param customer user entity with the inserted data
     * @return true if the data is valid, false otherwise
     */
    public boolean validateCustomer(Customer customer) {
        return validateName(customer.getFirstName()) && validateName(customer.getLastName()) && validateUsername(customer.
                getUsername()) && validateEmail(customer.getEmail()) && validatePassword(customer.getPassword()) &&
                validatePhone(customer.getPhoneNumber()) && validateName(customer.getAddress());
    }

    /**
     *
     * @param username the user's username
     * @return true if its length is of at least three characters and it does not contain the string "admin", false otherwise
     */
    @Override
    public boolean validateUsername(String username){
        return username.length() > 2 && !username.contains("admin");
    }

    /**
     *
     * @param password the user's password
     * @return true if its length is of at least 5 characters and it contains at least a digit, false otherwise
     */
    @Override
    public boolean validatePassword(String password) {
        boolean containsDigit = false;
        if(password.length() >= 5 )
            for (char c : password.toCharArray())
                if (Character.isDigit(c))
                    containsDigit = true;
        return containsDigit;
    }

    /**
     *
     * @param email the user's email address
     * @return true if it matches the given pattern, false otherwise
     */
    @Override
    public boolean validateEmail(String email) {
        return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();
    }

    /**
     *
     * @param name the user's name field
     * @return ture if the name begins with an upper letter and has a length of at least three letters, false otherwise
     */
    @Override
    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) return false;
        if (!Character.isUpperCase(name.toCharArray()[0]))
            return false;
        return name.length() > 2;
    }

    /**
     *
     * @param phone the user's phone number field
     * @return true if it only contains digits and has a length of exactly 10 digits, false otherwise
     */
    private boolean validatePhone(String phone){
        return phone.matches("[0-9]+") && phone.length() == 10;
    }

}
