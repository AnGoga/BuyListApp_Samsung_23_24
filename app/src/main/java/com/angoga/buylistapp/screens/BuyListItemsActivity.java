package com.angoga.buylistapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.angoga.buylistapp.R;
import com.angoga.buylistapp.callbacks.StartBuyListItemsCallback;
import com.angoga.buylistapp.databinding.ActivityBuyListItemsBinding;
import com.angoga.buylistapp.network.model.BuyListItemRequest;
import com.angoga.buylistapp.network.model.BuyListItemResponse;
import com.angoga.buylistapp.network.model.BuyListResponse;
import com.angoga.buylistapp.network.services.BuyListApi;
import com.angoga.buylistapp.network.services.RetrofitClient;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyListItemsActivity extends AppCompatActivity {
    private ActivityBuyListItemsBinding binding;
    private BuyListItemsAdapter adapter;
    private UUID buyListId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyListItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buyListId = UUID.fromString(getIntent().getStringExtra("id"));
        initView();
        initOnClicks();
    }

    private void initOnClicks() {
        binding.button.setOnClickListener(v -> {
            String name = binding.editTextName.getText().toString();
            String description = binding.editTextDescription.getText().toString();
            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(BuyListItemsActivity.this, R.string.enter_data, Toast.LENGTH_LONG).show();
                return;
            }
            BuyListItemRequest request = new BuyListItemRequest(name, description);
            RetrofitClient.getInstance().create(BuyListApi.class).createBuyListItem(request, buyListId).enqueue(new Callback<BuyListItemResponse>() {
                @Override
                public void onResponse(Call<BuyListItemResponse> call, Response<BuyListItemResponse> response) {
                    adapter.addNewBuyListItem(response.body());
                }

                @Override
                public void onFailure(Call<BuyListItemResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(BuyListItemsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void initView() {
        initRecyclerView();
        fetchData();
    }

    private void fetchData() {
        RetrofitClient.getInstance().create(BuyListApi.class).getBuyListById(buyListId).enqueue(new Callback<BuyListResponse>() {

            @Override
            public void onResponse(Call<BuyListResponse> call, Response<BuyListResponse> response) {
                adapter.setBuyListItems(response.body().getItems());
            }

            @Override
            public void onFailure(Call<BuyListResponse> call, Throwable t) {

            }
        });
    }

    private void initRecyclerView() {
        adapter = new BuyListItemsAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
}