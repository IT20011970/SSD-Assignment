package lk.agri.repository;

import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    @Query("from Cart where userAccount.email=?1 and purchased=false")
    Optional<Cart> getCart(String email);

    Optional<Cart> findByPurchased(boolean purchase);

}