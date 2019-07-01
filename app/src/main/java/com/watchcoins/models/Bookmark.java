package com.watchcoins.models;

import io.realm.RealmObject;

public class Bookmark extends RealmObject {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
