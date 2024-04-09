package com.angoga.buylistapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.angoga.buylistapp.R;
import com.angoga.buylistapp.databinding.ActivityMainBinding;
import com.angoga.buylistapp.network.model.BuyListRequest;
import com.angoga.buylistapp.network.model.BuyListResponse;
import com.angoga.buylistapp.network.model.ListOfBuyLists;
import com.angoga.buylistapp.network.services.BuyListApi;
import com.angoga.buylistapp.network.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BuyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initOnClicks();
    }

    private void initOnClicks() {
        binding.button.setOnClickListener(v -> {
            String name = binding.editTextName.getText().toString();
            String description = binding.editTextDescription.getText().toString();
            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.enter_data, Toast.LENGTH_LONG).show();
                return;
            }
            BuyListRequest request = new BuyListRequest(name, description);
            RetrofitClient.getInstance().create(BuyListApi.class).createBuyList(request).enqueue(new Callback<BuyListResponse>() {
                @Override
                public void onResponse(Call<BuyListResponse> call, Response<BuyListResponse> response) {
                    adapter.addNewBuyList(response.body());
                }

                @Override
                public void onFailure(Call<BuyListResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(MainActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void initView() {
        initRecyclerView();
        fetchData();
    }

    private void fetchData() {
        RetrofitClient.getInstance().create(BuyListApi.class).getAllBuyLists().enqueue(new Callback<ListOfBuyLists>() {
            @Override
            public void onResponse(Call<ListOfBuyLists> call, Response<ListOfBuyLists> response) {
                adapter.setBuyLists(response.body().getPage());
            }

            @Override
            public void onFailure(Call<ListOfBuyLists> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerView() {
        adapter = new BuyListAdapter((id) -> {
            Intent intent = new Intent(MainActivity.this, BuyListItemsActivity.class);
            intent.putExtra("id", id.toString());
            startActivity(intent);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
}