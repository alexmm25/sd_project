package backend.repository;

import backend.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartFoodsRepository extends JpaRepository<CartProduct, Long> {

    @Modifying
    @Transactional
    @Query("delete from CartProduct c WHERE c.cart.cartID = :id")
    void deleteAllByCartID(@Param("id") Long id);

}
