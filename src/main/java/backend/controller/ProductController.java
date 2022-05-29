package backend.controller;

import backend.model.Product;
import backend.service.utils.PDFExporter;
import backend.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getFood(@PathVariable Long id) {
        logger.info("Getting product with ID {}...", id);
        return service.getProductById(id);
    }

    @GetMapping("/S/{id}")
    public List<Product> getProductsBySponsor(@PathVariable Long id) {
        logger.info("Getting products from sponsor with ID {}...", id);
        return service.getProductsBySponsor(id);
    }

    @GetMapping(value = "/PDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> menuReport(@PathVariable Long id) {
        logger.info("Exporting menu as PDF for sponsor with ID {}....", id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=menu.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).
                body(new InputStreamResource(PDFExporter.createPDF(service.getProductsBySponsor(id))));
    }

    @PostMapping("/{id}")
    public ResponseEntity createProduct(@PathVariable Long id, @RequestBody Product product) {
        logger.info("Creating product {}...", product.getName());
        Product createdProduct = service.createProduct(id, product);
        if (createdProduct == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid data!");
        else return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        service.deleteByID(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existentProduct = service.getProductById(id);
        existentProduct.setName(product.getName());
        existentProduct.setStock(product.getStock());
        existentProduct.setCategory(product.getCategory());
        existentProduct.setPrice(product.getPrice());
        existentProduct.setDescription(product.getDescription());
        existentProduct = service.createProduct(0L, existentProduct);

        return ResponseEntity.ok(existentProduct);
    }

    @GetMapping("/getID/{name}")
    public ResponseEntity getIDByName(@PathVariable String name) {
        System.out.println(name);
        Long searchedID = service.getIDByName(name).getProductID();
        System.out.println(searchedID);
        if(searchedID > 0)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(searchedID);
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    }
}
