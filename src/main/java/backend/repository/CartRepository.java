package backend.repository;

import backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("update Cart c set c.price = :price WHERE c.cartID = :id")
    void updatePrice(@Param("id") Long id, @Param("price") Integer price);

}
