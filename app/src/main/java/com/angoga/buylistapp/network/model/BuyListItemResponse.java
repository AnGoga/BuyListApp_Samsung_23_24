package com.angoga.buylistapp.network.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BuyListItemResponse extends BuyListItemRequest {
    @SerializedName("id")
    private UUID id;
    @SerializedName("createdAt")
    private String createdAt;

    public BuyListItemResponse(String name, String description) {
        super(name, description);
    }

}
