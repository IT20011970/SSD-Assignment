package lk.agri.entity;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    private String itemId;
    private String description;
    private double price;
    private String imgName;
    private String imgType;
    private int qty;
    @ManyToOne
    private UserAccount userAccount;

    public Item() {
    }

    public Item(String itemId, String description, double price, int qty, String email) {
        this.itemId = itemId;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.userAccount = new UserAccount();
        this.userAccount.setEmail(email);
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
