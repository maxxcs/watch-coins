package com.watchcoins.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CurrencyDetailsModel {

    @SerializedName("data")
    @Expose
    private Currency data;
    @SerializedName("timestamp")
    @Expose
    private BigInteger timestamp;

    public Currency getData() {
        return data;
    }

    public void setData(Currency data) {
        this.data = data;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

}