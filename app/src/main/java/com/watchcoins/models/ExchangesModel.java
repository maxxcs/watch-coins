package com.watchcoins.models;

import java.math.BigInteger;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangesModel {

    @SerializedName("data")
    @Expose
    private List<Exchange> data = null;
    @SerializedName("timestamp")
    @Expose
    private BigInteger timestamp;

    public List<Exchange> getData() {
        return data;
    }

    public void setData(List<Exchange> data) {
        this.data = data;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

}