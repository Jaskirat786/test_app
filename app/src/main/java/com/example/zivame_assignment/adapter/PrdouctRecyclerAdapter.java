package com.example.zivame_assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zivame_assignment.databinding.DasboardItemBinding;
import com.example.zivame_assignment.model.Products;

import java.util.List;

public class PrdouctRecyclerAdapter extends RecyclerView.Adapter<PrdouctRecyclerAdapter.ProductViewHolder> {

    private List<Products> productList;
    private Context context;

    public PrdouctRecyclerAdapter(List<Products> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public void setProductList(List<Products> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        DasboardItemBinding binding = DasboardItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new ProductViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Products products = productList.get(position);
        holder.setList(products);
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private final DasboardItemBinding binding;
        private Products products;

        public ProductViewHolder(@NonNull DasboardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setList(Products products) {
            ;
            this.products = products;

            String rating= products.getRating()+ "";

            binding.productName.setText(products.getName());
            binding.productPrice.setText(products.getPrice());
            binding.productRating.setText(rating);
            Glide.with(context).load(products.getImageUrl()).into(binding.productImage);

        }
    }
}
