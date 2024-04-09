package com.angoga.buylistapp.screens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.angoga.buylistapp.R;
import com.angoga.buylistapp.callbacks.StartBuyListItemsCallback;
import com.angoga.buylistapp.databinding.BuyListItemBinding;
import com.angoga.buylistapp.network.model.BuyListItemResponse;

import java.util.ArrayList;
import java.util.List;

public class BuyListItemsAdapter extends RecyclerView.Adapter<BuyListItemsAdapter.BuyListItemViewHolder> {
    private List<BuyListItemResponse> buyLists = new ArrayList<>();

    @NonNull
    @Override
    public BuyListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BuyListItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListItemViewHolder holder, int position) {
        holder.update(buyLists.get(position));
    }

    @Override
    public int getItemCount() {
        return buyLists.size();
    }

    public void addNewBuyListItem(BuyListItemResponse obj) {
        buyLists.add(obj);
        notifyItemInserted(buyLists.size() - 1);
    }

    public void setBuyListItems(List<BuyListItemResponse> buyLists) {
        this.buyLists = buyLists;
        notifyDataSetChanged();
    }

    public class BuyListItemViewHolder extends RecyclerView.ViewHolder {
        private final BuyListItemBinding binding;

        public BuyListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BuyListItemBinding.bind(itemView);
            initOnClicks();
        }

        private void initOnClicks() {

        }

        public void update(BuyListItemResponse obj) {
            binding.textViewName.setText(obj.getName());
            binding.textViewDescription.setText(obj.getDescription());
        }
    }
}
