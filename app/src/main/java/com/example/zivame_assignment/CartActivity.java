package com.example.zivame_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.zivame_assignment.databinding.ActivityCartBinding;
import com.example.zivame_assignment.model.Products;
import com.example.zivame_assignment.viewmodel.CartViewModel;
import com.example.zivame_assignment.viewmodel.DashboardViewmodel;
import com.example.zivame_assignment.viewmodel.ProductsViewModelFactory;

import androidx.lifecycle.ViewModelProvider;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    private CartViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Integer productId = getIntent().getIntExtra("extra_product_id",0);

        ProductsViewModelFactory factory = new ProductsViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(CartViewModel.class);

        viewModel.product().observe(this, new Observer<Products>() {

            @Override
            public void onChanged(Products products) {

                String rating =products.getRating()+"";
                if (products != null){
                    binding.productName.setText(products.getName());
                binding.productRating.setText(rating);
                binding.productPrice.setText(products.getPrice());
                Glide.with(getApplicationContext()).load(products.getImageUrl()).into(binding.productImage);}
            }
        });
        viewModel.fetchProduct(productId);


        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CartActivity.this,
                        OrderPlacedActivity.class);
                startActivity(i);
            }
        });

    }
}