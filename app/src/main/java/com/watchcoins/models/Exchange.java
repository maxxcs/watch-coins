package com.watchcoins.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Exchange {

    @SerializedName("exchangeId")
    @Expose
    private String exchangeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("percentTotalVolume")
    @Expose
    private String percentTotalVolume;
    @SerializedName("volumeUsd")
    @Expose
    private String volumeUsd;
    @SerializedName("tradingPairs")
    @Expose
    private String tradingPairs;
    @SerializedName("socket")
    @Expose
    private Object socket;
    @SerializedName("exchangeUrl")
    @Expose
    private String exchangeUrl;
    @SerializedName("updated")
    @Expose
    private BigInteger updated;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPercentTotalVolume() {
        return percentTotalVolume;
    }

    public void setPercentTotalVolume(String percentTotalVolume) {
        this.percentTotalVolume = percentTotalVolume;
    }

    public String getVolumeUsd() {
        return volumeUsd;
    }

    public void setVolumeUsd(String volumeUsd) {
        this.volumeUsd = volumeUsd;
    }

    public String getTradingPairs() {
        return tradingPairs;
    }

    public void setTradingPairs(String tradingPairs) {
        this.tradingPairs = tradingPairs;
    }

    public Object getSocket() {
        return socket;
    }

    public void setSocket(Object socket) {
        this.socket = socket;
    }

    public String getExchangeUrl() {
        return exchangeUrl;
    }

    public void setExchangeUrl(String exchangeUrl) {
        this.exchangeUrl = exchangeUrl;
    }

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

}