package com.watchcoins.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketsModel {

    @SerializedName("data")
    @Expose
    private List<MarketplaceModel> data = null;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public List<MarketplaceModel> getData() {
        return data;
    }

    public void setData(List<MarketplaceModel> data) {
        this.data = data;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}