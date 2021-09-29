package com.example.zivame_assignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.zivame_assignment.model.Products;

import java.util.List;

@Dao
public interface ProductDao {

    //Performing various CRUD operations
    @Query("Select * from products")
    List<Products> getAllProducts();

    @Query("Select * from products WHERE ID =:id")
    Products getProduct(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Products> productList);

    @Query("Delete from products")
    void deleteAll();
}
;