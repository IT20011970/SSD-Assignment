package lk.agri.dto;


import lk.agri.entity.Item;
import lk.agri.entity.UserAccount;

public class ItemDTO {

    private String itemId;
    private String description;
    private double price;
    private String imgName;
    private String imgType;
    private int qty;
    private int bQty = 1;
    private int gVal;
    private int kgVal;

    private UserAccount userAccount;

    public ItemDTO(Item item) {
        this.itemId = item.getItemId();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.imgName = item.getImgName();
        this.imgType = item.getImgType();
        this.qty = item.getQty();
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

    public int getbQty() {
        return bQty;
    }

    public void setbQty(int bQty) {
        this.bQty = bQty;
    }

    public int getgVal() {
        return gVal;
    }

    public void setgVal(int gVal) {
        this.gVal = gVal;
    }

    public int getKgVal() {
        return kgVal;
    }

    public void setKgVal(int kgVal) {
        this.kgVal = kgVal;
    }
}
