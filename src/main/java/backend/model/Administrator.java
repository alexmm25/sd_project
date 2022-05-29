package backend.model;

public class Administrator {

    private String username = "admin";
    private String password = "admin";

    public Administrator(String username, String password){
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
