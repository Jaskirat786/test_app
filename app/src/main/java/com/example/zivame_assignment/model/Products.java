package com.example.zivame_assignment.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

@Entity(tableName = "products")
public class Products {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private Integer id;

    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "product_price")
    private String price;

    @SerializedName("image_url")
    @Expose
    @ColumnInfo(name = "product_images")
    private String imageUrl;

    @ColumnInfo(name = "product_rating")
    private Integer rating;


    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}