package service;

import backend.model.Product;
import backend.repository.CartFoodsRepository;
import backend.repository.CartRepository;
import backend.service.CartService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartFoodsRepository cartFoodsRepository;

    @InjectMocks
    CartService cartService;


    @Test
    public void clearCartTest() {
        List<Product> cartFoodsBefore = cartService.getCartProductsByCartId(11L);
        Assertions.assertNotNull(cartFoodsBefore);
        cartService.clearCart(6L);
        List<Product> cartFoodsAfter = cartService.getCartProductsByCartId(11L);
        Assertions.assertEquals(new ArrayList<>(), cartFoodsAfter);
    }

}
