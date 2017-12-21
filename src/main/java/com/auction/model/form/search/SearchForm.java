package com.auction.model.form.search;

import java.util.List;

/**
 * Created by Helga on 01.12.2017.
 */
public class SearchForm {
    private List<LotRestCreateForm> items;
    private int total_count;
    private int incomplete_results;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(int incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<LotRestCreateForm> getItems() {
        return items;
    }

    public void setItems(List<LotRestCreateForm> items) {
        this.items = items;
    }
}
