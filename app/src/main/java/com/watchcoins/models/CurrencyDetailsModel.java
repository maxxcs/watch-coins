package com.watchcoins.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CurrencyDetailsModel {

    @SerializedName("data")
    @Expose
    private CurrencyModel data;
    @SerializedName("timestamp")
    @Expose
    private BigInteger timestamp;

    public CurrencyModel getData() {
        return data;
    }

    public void setData(CurrencyModel data) {
        this.data = data;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

}