package com.example.zivame_assignment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName ="products")
public class ProductModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_content")
    @SerializedName("products")
    @Expose
   private List<Products> products = null;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}
