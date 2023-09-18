package lk.agri.dto;

import lk.agri.entity.CartDetail;

public class CartDetailDTO {

    private String cartDetailId;
    private int quantity;
    private ItemDTO item;

    public CartDetailDTO(CartDetail cartDetail) {
        this.cartDetailId = cartDetail.getCartDetailId();
        this.quantity = cartDetail.getQuantity();
        this.item = new ItemDTO(cartDetail.getItem());
    }

    public String getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(String cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
