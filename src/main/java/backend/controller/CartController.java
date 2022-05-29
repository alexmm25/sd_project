package backend.controller;

import backend.service.CartService;
import backend.model.Order;
import backend.model.Product;
import backend.service.utils.EmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    private static Logger logger = LogManager.getLogger(CartController.class);

    @Autowired
    private CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public List<Product> getCart(@PathVariable Long id) {
        logger.info("Getting the products from cart {}...", id);
        return service.getCartProductsByCartId(id);
    }

    @PostMapping("/{id}")
    public void addToCart(@PathVariable String id) {
        logger.info("Adding food to cart...");
        service.addProductToCart(Long.parseLong(id.split("-")[0]), id.split("-")[1]);
    }

    @DeleteMapping("/{id}")
    public void emptyCart(@PathVariable Long id){
        logger.info("Clearing cart...");
        service.clearCart(id);
    }

    @PostMapping("/O/{id}")
    public void orderDetails(@RequestBody Order order, @PathVariable Long id) {
        logger.info("Sending email...");
        new EmailSender().sendEmail(service.getCartProductsByCartId(id),
                order.getClientAddress(),
                order.getExtraDetails());
    }

}

