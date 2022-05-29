package service;

import backend.model.Product;
import backend.model.Sponsor;
import backend.repository.ProductRepository;
import backend.repository.SponsorRepository;
import backend.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SponsorRepository sponsorRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void validGetFoodByIdTest() {
        Product expectedProduct = new Product("Match Ball", 120, "OTHERS",
                "Round ish",  20, new Sponsor());
        Mockito.doReturn(Optional.of(expectedProduct)).when(productRepository).findById(5L);
        Product actualProduct = productService.getProductById(5L);
        Assertions.assertEquals(expectedProduct.getName(), actualProduct.getName());
    }

    @Test (expected = RuntimeException.class)
    public void invalidGetFoodByIdTest() {
        Mockito.doReturn(Optional.empty()).when(productRepository).findById(2L);
        Product actualProduct = productService.getProductById(2L);
        Assertions.assertNull(actualProduct.getName());
    }

    @Test
    public void validCreateFoodTest() {
        Product expectedProduct = new Product("Ball", 25, "KITS", "Not round",
                50, new Sponsor());
        Mockito.doReturn(expectedProduct).when(productRepository).save(expectedProduct);
        Product actualProduct = productService.createProduct(3L, expectedProduct);
        Assertions.assertEquals(expectedProduct.getName(), actualProduct.getName());
    }

    @Test (expected = NullPointerException.class)
    public void invalidCreateFoodTest() {
        Product expectedProduct = new Product("ball", 25, "kits", "square",
                50, new Sponsor());
        Mockito.doReturn(null).when(productRepository).save(expectedProduct);
        Product actualProduct = productService.createProduct(3L, expectedProduct);
        Assertions.assertNull(actualProduct.getName());
    }
}
