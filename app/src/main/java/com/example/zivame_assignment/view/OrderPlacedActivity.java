package com.example.zivame_assignment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.zivame_assignment.databinding.ActivityOrderPlacedBinding;

public class OrderPlacedActivity extends AppCompatActivity {


    private ActivityOrderPlacedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderPlacedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OrderPlacedActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}