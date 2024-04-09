package com.angoga.buylistapp.network.services;

import com.angoga.buylistapp.network.model.BuyListItemRequest;
import com.angoga.buylistapp.network.model.BuyListItemResponse;
import com.angoga.buylistapp.network.model.BuyListRequest;
import com.angoga.buylistapp.network.model.BuyListResponse;
import com.angoga.buylistapp.network.model.ListOfBuyLists;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BuyListApi {

    @POST("/buyList")
    Call<BuyListResponse> createBuyList(@Body BuyListRequest request);

    @GET("/buyList/list")
    Call<ListOfBuyLists> getAllBuyLists();

    @DELETE("/buyList/{id}")
    Call<BuyListResponse> deleteBuyListById(@Path("id") UUID id);

    @GET("/buyList/{id}")
    Call<BuyListResponse> getBuyListById(@Path("id") UUID id);

    @POST("/buyList/{id}/items")
    Call<BuyListItemResponse> createBuyListItem(@Body BuyListItemRequest request, @Path("id") UUID id);

}
