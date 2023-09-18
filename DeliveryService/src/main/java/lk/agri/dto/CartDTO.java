package lk.agri.dto;

import lk.agri.entity.Cart;
import lk.agri.entity.CartDetail;
import lk.agri.entity.Item;
import lk.agri.entity.UserAccount;

import java.time.LocalDate;
import java.util.List;

public class CartDTO {
    private String cartId;
    private LocalDate purchasedAt;
    private boolean purchased;
    private List<CartDetailDTO> cartDetails;

    public CartDTO() {
    }

    public CartDTO(Cart cart) {
        this.cartId = cart.getCartId();
        this.purchasedAt = cart.getPurchasedAt();
        this.purchased = cart.isPurchased();
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public LocalDate getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalDate purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public List<CartDetailDTO> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetailDTO> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
