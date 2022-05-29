package backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private Long cartID;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts = new ArrayList<>();

    @Column
    private Integer price = 0;

    public Cart() {}

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void addCartFood(CartProduct cartFood){
        cartProducts.add(cartFood);
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

}
