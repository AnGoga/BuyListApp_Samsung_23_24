package com.angoga.buylistapp.network.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BuyListResponse extends BuyListRequest {
    @SerializedName("id")
    private UUID id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("items")

    private List<BuyListItemResponse> items;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<BuyListItemResponse> getItems() {
        return items;
    }

    public void setItems(List<BuyListItemResponse> items) {
        this.items = items;
    }

    public BuyListResponse(String name, String description) {
        super(name, description);
    }

}
