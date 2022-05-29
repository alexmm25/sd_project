package backend.service.validators;

public interface Validator {

    boolean validateUsername(String username);
    boolean validatePassword(String password);
    boolean validateEmail(String email);
    boolean validateName(String name);
}
