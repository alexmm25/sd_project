package backend.repository;

import backend.model.Sponsor;
import backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT f FROM Product f where f.name = :name")
    List<Product> findProductByName(String name);

    @Query("SELECT f FROM Product f where f.sponsor = :sponsor")
    List<Product> findProductsBySponsor(@Param("sponsor") Sponsor sponsor);

    @Query("SELECT f FROM Product f where f.name = :name")
    Product findIDByName(@Param("name") String name);

//    @Query("SELECT f FROM Product f where f.name = :name")
//    void updateProductStock(@Param("productID") Long productID, @Param("stock") Integer stock);
}
