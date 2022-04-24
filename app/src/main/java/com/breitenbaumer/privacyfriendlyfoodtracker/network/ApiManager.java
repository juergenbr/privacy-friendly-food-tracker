/*
This file is part of Privacy friendly food tracker.

Privacy friendly food tracker is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Privacy friendly food tracker is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Privacy friendly food tracker.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.breitenbaumer.privacyfriendlyfoodtracker.network;


import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Creates the open food facts api service to send out network requests to the web-api.
 *
 * @author Andre Lutz
 */
public class ApiManager implements IApiManager {

    private static ApiManager instance;
    private ProductApiService productApiService;


    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private ApiManager() {
    }

    /**
     * Returns the product api service. Uses the current default language code (de=Germany search, otherwise=Global search) to define the base url.
     *
     * @return product api service
     */
    @Override
    public ProductApiService getProductApiService() {
        if (productApiService == null) {
            productApiService = createProductApiService();
        }

        return productApiService;
    }

    /**
     * Returns the product api service.
     *
     * @param languageCode the locale on which the products will be searched (de=Germany search, otherwise=Global search) to define the base url
     * @return product api service
     */
    @Override
    public ProductApiService getProductApiService(String languageCode) {
        if (productApiService == null) {
            productApiService = createProductApiService(languageCode);
        }

        return productApiService;
    }

    private ProductApiService createProductApiService() {
        String languageCode = Locale.getDefault().getCountry();
        // TODO: check if it supported -> https://world.openfoodfacts.org/data/taxonomies/countries.json
        if (languageCode.equals("")) {
            languageCode = "world";
        }
        productApiService = createProductApiService(languageCode);

        return productApiService;
    }


    private ProductApiService createProductApiService(String languageCode) {
        productApiService = new Retrofit.Builder()
                .baseUrl("https://" + languageCode + ".openfoodfacts.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductApiService.class);

        return productApiService;
    }
}