package com.example.zivame_assignment.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.zivame_assignment.databinding.ActivityCartBinding;
import com.example.zivame_assignment.model.Products;
import com.example.zivame_assignment.viewmodel.CartViewModel;
import com.example.zivame_assignment.viewmodel.ProductsViewModelFactory;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    private CartViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding  activity with xml layout
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Integer productId = getIntent().getIntExtra("extra_product_id", 0);

        //Initializiation  of Modelfactory for viewmodel
        ProductsViewModelFactory factory = new ProductsViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(CartViewModel.class);

        viewModel.product().observe(this, new Observer<Products>() {
            @Override
            public void onChanged(Products products) {

                String rating = products.getRating() + "";
                if (products != null) {
                    binding.productName.setText(products.getName());
                    binding.productRating.setText(rating);
                    binding.productPrice.setText(products.getPrice());
                    Glide.with(getApplicationContext()).load(products.getImageUrl()).into(binding.productImage);
                }
            }
        });
        viewModel.fetchProduct(productId);

        //checking out from cart by clicking on checkout button
        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showing progress bar
                binding.bar.setVisibility(view.getVisibility());
                binding.loadingText.setVisibility(view.getVisibility());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.bar.setVisibility(View.GONE);
                        binding.loadingText.setVisibility(View.GONE);
                        Intent i = new Intent(CartActivity.this, OrderPlacedActivity.class);
                        startActivity(i);
                    }
                }, 30000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        binding.bar.setVisibility(View.GONE);
        binding.loadingText.setVisibility(View.GONE);
        super.onBackPressed();
    }
}