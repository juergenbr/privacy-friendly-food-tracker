package com.breitenbaumer.privacyfriendlyfoodtracker;

import static org.junit.Assert.assertTrue;

import com.breitenbaumer.privacyfriendlyfoodtracker.network.ApiManager;
import com.breitenbaumer.privacyfriendlyfoodtracker.network.ProductApiService;
import com.breitenbaumer.privacyfriendlyfoodtracker.network.models.NetworkProduct;
import com.breitenbaumer.privacyfriendlyfoodtracker.network.models.ProductResponse;
import com.breitenbaumer.privacyfriendlyfoodtracker.network.utils.ProductConversionHelper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiManagerTest {
    ProductApiService mProductApiService = null ;
    ProductResponse productResponse = new ProductResponse();
    List<com.breitenbaumer.privacyfriendlyfoodtracker.database.Product> products = new ArrayList<>();

    @Before
    public void createManager() {
        this.mProductApiService =  ApiManager.getInstance().getProductApiService("de");

    }

    @Test
    public void readProductInformation() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        Call<ProductResponse> call = mProductApiService.listProducts("banane");
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful())  {
                    productResponse = response.body();
                    }
                else{
                    //show error
                }
                signal.countDown();

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                signal.countDown();

            }
        });
        signal.await();// wait for callback
        // uses the current location. To pass the test, the location must be "de"
        assertTrue("Responds should contains 20 product informations ", productResponse.getProducts().size() >= 1);
    }

    @Test
    public void readProductInformationConverter() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        Call<ProductResponse> call = mProductApiService.listProducts("banane");

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful())  {
                    // Conversion to db product
                    productResponse = response.body();
                    for(int i = 0; i < productResponse.getProducts().size();i++){
                        NetworkProduct product = productResponse.getProducts().get(i);
                        if(product != null){
                        products.add(ProductConversionHelper.conversionProduct(product));
                        }
                    }
                }
                else{
                    //show error
                }
                signal.countDown();

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                signal.countDown();

            }
        });
        signal.await();// wait for callback
        // uses the current location. To pass the test, the location must be "de"
        assertTrue("Responds should contains 20 product informations ", products.get(0).energy != 0 );
    }
}
