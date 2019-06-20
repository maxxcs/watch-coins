package com.watchcoins.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrenciesModel {

    @SerializedName("data")
    @Expose
    private List<CurrencyModel> data = null;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public List<CurrencyModel> getData() {
        return data;
    }

    public void setData(List<CurrencyModel> data) {
        this.data = data;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}