package com.breitenbaumer.privacyfriendlyfoodtracker.database.importexport;

import com.breitenbaumer.privacyfriendlyfoodtracker.database.ConsumedEntries;
import com.breitenbaumer.privacyfriendlyfoodtracker.database.Product;

import java.util.List;

public class ImpExModel {
    private List<Product> productList;
    private List<ConsumedEntries> consumedEntriesList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ConsumedEntries> getConsumedEntriesList() {
        return consumedEntriesList;
    }

    public void setConsumedEntriesList(List<ConsumedEntries> consumedEntriesList) {
        this.consumedEntriesList = consumedEntriesList;
    }
}
