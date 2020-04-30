package controller;

import model.Filter;
import model.Product;
import model.Sort;

import java.util.ArrayList;

public class SearchingManager extends Manager {

    public SearchingManager() {
    }

    public ArrayList<Filter> getCurrentFilters() {
        return super.currentFilters;
    }

    public ArrayList<Sort> getCurrentSorts() {
        return super.currentSorts;
    }

    public ArrayList<Product> performFilter(String filterTag , String info) throws Exception {
        Filter filter = storage.getFilterByName(filterTag);
        switch(filterTag)
        {
            case "category":
                return filter.filterByCategory(storage.getCategoryByName(info),storage.getAllProducts());
            case "name":
                return filter.filterByName(info,storage.getAllProducts());
            case "price":
                return filter.filterByPrice(Double.parseDouble(info),storage.getAllProducts());
            default:
                throw new Exception("Ops !! No match with available filter tags!");
        }
    }

    public ArrayList<Product> performSort (String sortTag) throws Exception {
        Sort sort = storage.getSortByName(sortTag);
        switch(sortTag)
        {
            case "category":
                sort.sortByCategory(storage.getAllCategories());
            case "price":
                return sort.sortByPrice(storage.getAllProducts());
            default:
                throw new Exception("Ops !! No match with available sort tags!");
        }
    }
}
