package com.angoga.buylistapp.network.model;

import com.google.gson.annotations.SerializedName;

public class BuyListItemRequest {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;

    public BuyListItemRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
