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
        if (storage.getFilterByName(filterTag) == null){
            throw new Exception("This filter is not available!");
        }
        Filter filter = storage.getFilterByName(filterTag);
        if (currentFilters.contains(filter)){
            throw new Exception("This filter is already selected!");
        }
        switch(filterTag)
        {
            case "category":
                currentFilters.add(filter);
                return filter.filterByCategory(storage.getCategoryByName(info),storage.getAllProducts());
            case "name":
                currentFilters.add(filter);
                return filter.filterByName(info,storage.getAllProducts());
            case "price":
                currentFilters.add(filter);
                return filter.filterByPrice(Double.parseDouble(info),storage.getAllProducts());
            default:
                throw new Exception("Ops !! No match with available filter tags!");
        }
    }

    public ArrayList<Product> performSort (String sortTag) throws Exception {
        if (storage.getSortByName(sortTag) == null){
            throw new Exception("This sort is not available!");
        }
        Sort sort = storage.getSortByName(sortTag);
        if (currentSorts.contains(sort)){
            throw new Exception("This sort is already selected!");
        }
        switch(sortTag)
        {
            case "category":
                currentSorts.add(sort);
                sort.sortByCategory(storage.getAllCategories());
            case "price":
                currentSorts.add(sort);
                return sort.sortByPrice(storage.getAllProducts());
            default:
                throw new Exception("Ops !! No match with available sort tags!");
        }
    }

    public ArrayList<Product> disableFilter (String filterTag , String info) throws Exception {
        if(storage.getFilterByName(filterTag) == null)
            throw new Exception("This filter is not available");
        else if (!currentFilters.contains(storage.getFilterByName(filterTag)))
            throw new Exception("You did not select this filter");
        else{
            currentFilters.remove(storage.getFilterByName(filterTag));
            return performFilter(filterTag,info);
        }
    }

    public ArrayList<Product> disableSort (String sortTag) throws Exception {
        if(storage.getSortByName(sortTag) == null)
            throw new Exception("This sort is not available");
        else if (!currentSorts.contains(storage.getSortByName(sortTag)))
            throw new Exception("You did not select this sort");
        else{
            currentSorts.remove(storage.getSortByName(sortTag));
            return performSort(sortTag);
        }
    }
}
