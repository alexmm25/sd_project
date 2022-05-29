package backend.repository;

import backend.model.Cart;
import backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional
    @Modifying
    @Query("update Customer u set u.cart = :cart WHERE u.userID = :id")
    void updateUserCart(@Param("id") Long id, @Param("cart") Cart cart);

    String findFirstNameByLastName(String lastName);
}
