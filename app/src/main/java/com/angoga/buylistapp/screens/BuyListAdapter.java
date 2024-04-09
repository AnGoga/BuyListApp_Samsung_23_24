package com.angoga.buylistapp.screens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.angoga.buylistapp.R;
import com.angoga.buylistapp.callbacks.StartBuyListItemsCallback;
import com.angoga.buylistapp.databinding.BuyListItemBinding;
import com.angoga.buylistapp.network.model.BuyListResponse;
import com.angoga.buylistapp.network.services.BuyListApi;
import com.angoga.buylistapp.network.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {
    private List<BuyListResponse> buyLists = new ArrayList<>();

    private StartBuyListItemsCallback callback;
    public BuyListAdapter(StartBuyListItemsCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public BuyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BuyListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListViewHolder holder, int position) {
        holder.update(buyLists.get(position));
    }

    @Override
    public int getItemCount() {
        return buyLists.size();
    }

    public void addNewBuyList(BuyListResponse obj) {
        buyLists.add(obj);
        notifyItemInserted(buyLists.size() - 1);
    }

    public void setBuyLists(List<BuyListResponse> buyLists) {
        this.buyLists = buyLists;
        notifyDataSetChanged();
    }

    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        private final BuyListItemBinding binding;

        public BuyListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BuyListItemBinding.bind(itemView);
            initOnClicks();
        }

        private void initOnClicks() {
            binding.getRoot().setOnClickListener(v -> {
                callback.start(buyLists.get(getLayoutPosition()).getId());
            });

            binding.getRoot().setOnLongClickListener(v -> {
                RetrofitClient.getInstance()
                        .create(BuyListApi.class)
                        .deleteBuyListById(buyLists.get(getLayoutPosition()).getId()).enqueue(new Callback<BuyListResponse>() {
                            @Override
                            public void onResponse(Call<BuyListResponse> call, Response<BuyListResponse> response) {
                                buyLists.remove(getLayoutPosition());
                                notifyItemRemoved(getLayoutPosition());
                            }

                            @Override
                            public void onFailure(Call<BuyListResponse> call, Throwable t) {

                            }
                        });
                return false;
            });
        }

        public void update(BuyListResponse obj) {
            binding.textViewName.setText(obj.getName());
            binding.textViewDescription.setText(obj.getDescription());
        }
    }
}
