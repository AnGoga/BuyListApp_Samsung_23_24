package com.angoga.buylistapp.network.model;

import java.util.List;

public class ListOfBuyLists {
    private List<BuyListResponse> page;

    public ListOfBuyLists(List<BuyListResponse> page) {
        this.page = page;
    }

    public List<BuyListResponse> getPage() {
        return page;
    }

    public void setPage(List<BuyListResponse> page) {
        this.page = page;
    }
}
