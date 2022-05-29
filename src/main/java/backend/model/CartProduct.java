package backend.model;

import javax.persistence.*;

@Entity
@Table(name = "cart_products")
public class CartProduct {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long cartProductID;

    @ManyToOne
    @JoinColumn(name = "cartID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    public CartProduct(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    public CartProduct() {}

    public Cart getCart() {
        return cart;
    }

    public Product getFood() {
        return product;
    }

}
