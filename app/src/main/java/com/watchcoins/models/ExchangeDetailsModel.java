package com.watchcoins.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class ExchangeDetailsModel {

    @SerializedName("data")
    @Expose
    private Exchange data;
    @SerializedName("timestamp")
    @Expose
    private BigInteger timestamp;

    public Exchange getData() {
        return data;
    }

    public void setData(Exchange data) {
        this.data = data;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

}