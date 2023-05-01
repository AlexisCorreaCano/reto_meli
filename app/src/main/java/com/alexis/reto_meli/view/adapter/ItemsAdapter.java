package com.alexis.reto_meli.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexis.domain.model.Result;
import com.alexis.reto_meli.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private ArrayList<Result> results;
    private final OnItemClickListener listener;

    public ItemsAdapter(ArrayList<Result> results, OnItemClickListener listener){

        this.results = results;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return  new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tv_title.setText(results.get(position).title);
        holder.tv_price.setText(Double.toString(results.get(position).price));
        holder.tv_credit.setText("en "+results.get(position).installments.quantity+"X $"+results.get(position).installments.amount);
        Picasso.get().load(results.get(position).thumbnail).placeholder(R.drawable.imagen)
                .error(R.drawable.error).into(holder.im_item);
        holder.bind(results.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_title;
        private final TextView tv_price;
        private final TextView tv_offer;
        private final TextView tv_credit;
        private final ImageView im_item;

        public ItemViewHolder(View view){
            super(view);

            tv_title = view.findViewById(R.id.tv_title);
            tv_price = view.findViewById(R.id.tv_price);
            tv_offer =view.findViewById(R.id.tv_offer);
            tv_credit = view.findViewById(R.id.tv_credit);
            im_item = view.findViewById(R.id.im_item);
        }

        public void bind(final Result item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
