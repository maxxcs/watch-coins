package com.watchcoins.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketplaceModel {

    @SerializedName("exchangeId")
    @Expose
    private String exchangeId;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("baseSymbol")
    @Expose
    private String baseSymbol;
    @SerializedName("baseId")
    @Expose
    private String baseId;
    @SerializedName("quoteSymbol")
    @Expose
    private String quoteSymbol;
    @SerializedName("quoteId")
    @Expose
    private String quoteId;
    @SerializedName("priceQuote")
    @Expose
    private String priceQuote;
    @SerializedName("priceUsd")
    @Expose
    private String priceUsd;
    @SerializedName("volumeUsd24Hr")
    @Expose
    private String volumeUsd24Hr;
    @SerializedName("percentExchangeVolume")
    @Expose
    private String percentExchangeVolume;
    @SerializedName("tradesCount24Hr")
    @Expose
    private String tradesCount24Hr;
    @SerializedName("updated")
    @Expose
    private Integer updated;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public void setBaseSymbol(String baseSymbol) {
        this.baseSymbol = baseSymbol;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getQuoteSymbol() {
        return quoteSymbol;
    }

    public void setQuoteSymbol(String quoteSymbol) {
        this.quoteSymbol = quoteSymbol;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(String priceQuote) {
        this.priceQuote = priceQuote;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getVolumeUsd24Hr() {
        return volumeUsd24Hr;
    }

    public void setVolumeUsd24Hr(String volumeUsd24Hr) {
        this.volumeUsd24Hr = volumeUsd24Hr;
    }

    public String getPercentExchangeVolume() {
        return percentExchangeVolume;
    }

    public void setPercentExchangeVolume(String percentExchangeVolume) {
        this.percentExchangeVolume = percentExchangeVolume;
    }

    public String getTradesCount24Hr() {
        return tradesCount24Hr;
    }

    public void setTradesCount24Hr(String tradesCount24Hr) {
        this.tradesCount24Hr = tradesCount24Hr;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

}