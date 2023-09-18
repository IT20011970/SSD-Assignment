package lk.agri.dto;

import lk.agri.entity.UserAccount;

public class UserAccountDTO {
    private String accountType;
    private String name;
    private String address;
    private String contactNo;
    private String email;
    private String password;

    public UserAccountDTO(UserAccount userAccount) {
        if (userAccount != null) {
            this.accountType = userAccount.getAccountType();
            this.name = userAccount.getName();
            this.address = userAccount.getAddress();
            this.contactNo = userAccount.getContactNo();
            this.email = userAccount.getEmail();
        }
//        this.password = userAccount.getPassword();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
