package com.example.product.Objects;

public class ProfileItems {

    String mobileNumber;
    String email;
    String password;
    String name;
    boolean isExpanded;

    public ProfileItems(String name, String mobileNumber, String email, String password) {
        this.email = email;
        this.mobileNumber= mobileNumber;
        this.name = name;
        this.password = password;
    }

    public boolean isExpanded(){
        return isExpanded;
    }

    public void setExpanded(boolean expanded){
        isExpanded = expanded;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
