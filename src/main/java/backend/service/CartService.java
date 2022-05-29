package backend.service;

import backend.model.Cart;
import backend.model.CartProduct;
import backend.model.Product;
import backend.repository.CartFoodsRepository;
import backend.repository.CartRepository;
import backend.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static Logger logger = LogManager.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartFoodsRepository cartFoodsRepository;

    /**
     *
     * @param id the id for the desired cart
     * @return a list of foods contained in the searched cart at the given moment of time
     */
    public List<Product> getCartProductsByCartId(Long id) {
        List<CartProduct> cartFoodsList = cartFoodsRepository.findAll();
        List<Product> foods = new ArrayList<>();
        cartFoodsList.forEach(c -> {
            if (c.getCart().getCartID().equals(id))
                foods.add(c.getFood());
        });
        if (foods.isEmpty()) logger.warn("Cart {} is empty!", id);
        else logger.info("Got all the products from cart {} successfully!", id);
        return foods;
    }

    /**
     *
     * @param id the id of the cart
     * @param foodName the food name used to identify the food to be added to the cart
     */
    public void addProductToCart(Long id, String foodName) {
        Cart cart = cartRepository.findById(id).orElseThrow(RuntimeException::new);
        Product product = productRepository.findProductByName(foodName).get(0);
        CartProduct cartFoods = new CartProduct(cart, product);
        cartFoodsRepository.save(cartFoods);
        cart.addCartFood(cartFoods);
        logger.info("Product {} added successfully to cart", foodName);
        cart.setPrice(cart.getPrice() + product.getPrice());
    }

    /**
     *
     * @param id the cart whose food list is to be made empty
     */
    public void clearCart(Long id) {
        getCartProductsByCartId(id).forEach(product -> {
            product.setStock(product.getStock() - 1);
            productRepository.save(product);
        });
        cartFoodsRepository.deleteAllByCartID(id);
        logger.info("Cart {} cleared successfully!", id);
    }
}
