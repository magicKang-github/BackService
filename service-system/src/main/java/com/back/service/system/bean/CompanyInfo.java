package com.back.service.system.bean;

/**
 * 公司基本信息
 * @author magicHat
 */
public class CompanyInfo {

    private String nameWhole;
    private String nameSmall;
    private String nameEnglish;
    private String address;
    private String telephone;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String email;

    public String getNameWhole() {
        return nameWhole;
    }

    public void setNameWhole(String nameWhole) {
        this.nameWhole = nameWhole;
    }

    public String getNameSmall() {
        return nameSmall;
    }

    public void setNameSmall(String nameSmall) {
        this.nameSmall = nameSmall;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyInfo(String nameWhole, String nameSmall, String nameEnglish, String address, String telephone, String phoneNumber, String email) {
        this.nameWhole = nameWhole;
        this.nameSmall = nameSmall;
        this.nameEnglish = nameEnglish;
        this.address = address;
        this.telephone = telephone;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
