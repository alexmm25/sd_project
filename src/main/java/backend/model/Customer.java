package backend.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("customer")
public class Customer extends User {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String phoneNumber;


    @OneToOne
    @JoinColumn(name = "cartID")
    private Cart cart;

    public Customer(String address,
                    String firstName,
                    String lastName,
                    String password,
                    String phoneNumber,
                    String username,
                    String email) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String username, String password) {
        super(username, password, "");
    }

    public Customer() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }

}
