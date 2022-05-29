package backend.service.validators;

import backend.model.Product;
import backend.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductValidator {

    private static Logger logger = LogManager.getLogger(ProductService.class);

    public boolean validateProduct(Product product) {
        return validateString(product.getName()) && validatePrice(product.getPrice()) &&
                validateCategory(product.getCategory()) && validateString(product.getDescription());
    }

    /**
     *
     * @param str the string to be validated
     * @return true if it starts with an upper case letter and its length is above 3 characters, false otherwise
     */
    private boolean validateString(String str) {
        return str.length() >= 3 && str.charAt(0) >= 'A' && str.charAt(0) <= 'Z';
    }

    /**
     *
     * @param price the price to be validated
     * @return true if it is positive, false otherwise
     */
    private boolean validatePrice(Integer price) {
        if (price <= 0) logger.error("Negative food price!");
        return price > 0;
    }

    /**
     *
     * @param category the category field to be validated
     * @return true if it is one of LUNCH, MEAL, DINNER, false otherwise
     */
    private boolean validateCategory(String category) {
        if (category.equals("KITS") || category.equals("TICKETS") || category.equals("OTHERS"))
            return true;
        else{
            logger.error("Food insertion failed. Invalid food category!");
            return false;
        }
    }
}
