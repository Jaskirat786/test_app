package com.example.zivame_assignment.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.model.Products;
import java.util.concurrent.ExecutorService;

public class CartViewModel extends ViewModel {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public CartViewModel(ProductDao productDao, ExecutorService executorService) {
        this.productDao = productDao;
        this.executorService = executorService;
    }

    //Fetching products according to id
    public void fetchProduct(Integer id) {

        executorService.execute(() -> {
            Products product = productDao.getProduct(id);

            if (product != null ) {
                _productLiveData.postValue(product);
                return;
            }
        });
    }

    private final MutableLiveData<Products> _productLiveData = new MutableLiveData<>();

    public LiveData<Products> product() {
        return _productLiveData;
    }


}