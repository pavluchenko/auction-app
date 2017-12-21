package com.auction.model.form.soket;

/**
 * Created by Helga on 21.11.2017.
 */
public class RateMessage {
    private String rateMessage;
    private String price;
    private String userSetRate;
    private String lotId;
    private String userEmail;

    public RateMessage() {
    }

    public RateMessage(String rateMessage) {
        this.rateMessage = rateMessage;
    }

    public String getRateMessage() {
        return rateMessage;
    }

    public void setRateMessage(String rateMessage) {
        this.rateMessage = rateMessage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserSetRate() {
        return userSetRate;
    }

    public void setUserSetRate(String userSetRate) {
        this.userSetRate = userSetRate;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
