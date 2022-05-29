package backend.service;

import backend.model.Product;
import backend.repository.ProductRepository;
import backend.repository.SponsorRepository;
import backend.service.validators.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SponsorRepository sponsorRepository;

    /**
     *
     * @return a list of all the foods from the database
     */
    public List<Product> getAllProducts() {
        logger.info("Got all products successfully!");
        return productRepository.findAll();
    }

    /**
     *
     * @param id the searched food's id
     * @return the food entity having the given id
     */
    public Product getProductById(Long id) {
        Product searchedProduct = productRepository.findById(id).orElseThrow(RuntimeException::new);
        logger.info("Got product {} successfully!", searchedProduct.getName());
        return searchedProduct;
    }

    /**
     *
     * @param product the product entity with data inserted in the frontend side
     * @return the saved product entity if data is valid, null otherwise
     */
    public Product createProduct(Long id, Product product) {
        if (new ProductValidator().validateProduct(product)) {
            logger.info("Product {} was successfully created!", product.getName());
            product.setSponsor(sponsorRepository.getById(id));
            return productRepository.save(product);
        }
        else{
            logger.info("Product {} creation failed. Invalid data!", product.getName());
            return null;
        }
    }

    /**
     *
     * @param id the sponsor's id
     * @return a list of products created by the desired sponsor
     */
    public List<Product> getProductsBySponsor(Long id) {
        return productRepository.findProductsBySponsor(sponsorRepository.getById(id));
    }

    /**
     *
     * @param id id of the product to be deleted
     */
    public void deleteByID(Long id) {
        productRepository.deleteById(id);
    }

    /**
     *
     * @param name the product's name
     * @return the id of the product having the desired name
     */
    public Product getIDByName(String name) {
        try {
            return productRepository.findIDByName(name);
        } catch (Exception e) {
            return null;
        }
    }

}
