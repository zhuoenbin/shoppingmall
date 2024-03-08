package com.ispan.projectX.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long creditId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "card_number", length = 16, nullable = false)
    private String cardNumber;

    @Column(name = "card_CVC_code", length = 4, nullable = false)
    private String cardCVCCode;

    @Column(name = "card_expiry_year", nullable = false)
    private int cardExpiryYear;

    @Column(name = "card_expiry_month", nullable = false)
    private int cardExpiryMonth;

    @Column(name = "card_holder_name", length = 100, nullable = false)
    private String cardHolderName;

    @Column(name = "city", length = 10)
    private String city;

    @Column(name = "district", length = 10)
    private String district;

    @Column(name = "address", length = 50)
    private String address;

    public CreditCard() {
    }

    public CreditCard(Long creditId, Users user, String cardNumber, String cardCVCCode, int cardExpiryYear, int cardExpiryMonth, String cardHolderName, String city, String district, String address) {
        this.creditId = creditId;
        this.user = user;
        this.cardNumber = cardNumber;
        this.cardCVCCode = cardCVCCode;
        this.cardExpiryYear = cardExpiryYear;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardHolderName = cardHolderName;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVCCode() {
        return cardCVCCode;
    }

    public void setCardCVCCode(String cardCVCCode) {
        this.cardCVCCode = cardCVCCode;
    }

    public int getCardExpiryYear() {
        return cardExpiryYear;
    }

    public void setCardExpiryYear(int cardExpiryYear) {
        this.cardExpiryYear = cardExpiryYear;
    }

    public int getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public void setCardExpiryMonth(int cardExpiryMonth) {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreditCard{");
        sb.append("creditId=").append(creditId);
        sb.append(", user=").append(user);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardCVCCode='").append(cardCVCCode).append('\'');
        sb.append(", cardExpiryYear=").append(cardExpiryYear);
        sb.append(", cardExpiryMonth=").append(cardExpiryMonth);
        sb.append(", cardHolderName='").append(cardHolderName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }

}

