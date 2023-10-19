package lk.agri.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartPK  implements Serializable {

    private String email;
    private String itemId ;
    private boolean purchased;

    public CartPK(String email, String itemId, boolean purchased) {
        this.email = email;
        this.itemId = itemId;
        this.purchased = purchased;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public CartPK() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCardId() {
        return itemId;
    }

    public void setCardId(String itemId) {
        this.itemId = itemId;
    }
}
