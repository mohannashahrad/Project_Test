package controller;

import model.Filter;
import model.Product;
import model.Sort;

import java.util.ArrayList;

public class SearchingManager extends Manager {

    private ProductManager productManager = new ProductManager();

    public SearchingManager() {

    }

    public ArrayList<Filter> getCurrentFilters() {
        return currentFilters;
    }

    public ArrayList<Sort> getCurrentSorts() {
        return currentSorts;
    }

    public ArrayList<Product> viewAllProducts(){
        return processOfViewProduct(storage.getAllProducts());
    }

    public ArrayList<Product> viewAllProductsInSale(){
        return processOfViewProduct(productManager.viewAllProductsWithSale());
    }

    private ArrayList<Product> processOfViewProduct (ArrayList <Product> selectedProducts){
        return sortProducts(filterProducts(selectedProducts));
    }

    private ArrayList<Product> filterProducts(ArrayList<Product> products){
        if (currentFilters.isEmpty()){
            return products;
        }
        else {
            ArrayList<Product> temp = new ArrayList<>();
            for (Filter filter : currentFilters) {
                if (filter.getFilterName().equals("category"))
                    temp.addAll(filter.filterByCategory(storage.getCategoryByName(filter.getFilterInfo()),products));
                if (filter.getFilterName().equals("name"))
                    temp.addAll(filter.filterByName(filter.getFilterInfo(),products));
                if (filter.getFilterName().equals("price"))
                    temp.addAll(filter.filterByPrice(Double.parseDouble(filter.getFilterInfo()),products));
            }
            return temp;
        }
    }

    private ArrayList<Product> sortProducts (ArrayList<Product> products){
        if (currentSorts.isEmpty()){
            Sort sort = new Sort("");
            return sort.defaultSort(products);
        }
        else{
            ArrayList<Product> temp = new ArrayList<>();
            for (Sort sort : currentSorts) {
                if (sort.getSortName().equals("price"))
                    temp.addAll(sort.sortByPrice(products));
                if (sort.getSortName().equals("average rate"))
                    temp.addAll(sort.sortByAverageRate(products));
            }
            return temp;
        }
    }

    public ArrayList<Product> performFilter(String filterTag, String info) throws Exception {
        for (Filter filter : currentFilters) {
            if (filter.getFilterName().equals(filterTag) && filter.getFilterInfo().equals(info))
                throw new Exception("This filter is already selected!");
        }
            Filter filter = new Filter(filterTag,info);
            storage.addFilter(filter);
            currentFilters.add(filter);
            return viewAllProducts();
    }

    public ArrayList<Product> performSort(String sortTag) throws Exception {
        for (Sort sort : currentSorts) {
            if (sort.getSortName().equals(sortTag))
                throw new Exception("This sort is already selected!");
        }
        Sort sort = new Sort(sortTag);
        storage.addSort(sort);
        currentSorts.add(sort);
        return viewAllProducts();
    }

    public ArrayList<Product> disableFilter(String filterTag, String info) throws Exception {
        Filter removedFilter = null;
        for (Filter filter : currentFilters) {
            if (filter.getFilterName().equals(filterTag) && filter.getFilterInfo().equals(info)) {
                removedFilter = filter;
            }
        }
        if(removedFilter == null){
            throw new Exception("You did not select this filter");
        }
        else {
            currentFilters.remove(removedFilter);
            return viewAllProducts();
        }
    }

    public ArrayList<Product> disableSort(String sortTag) throws Exception {
        Sort removedSort = null;
        for (Sort sort : currentSorts) {
            if(sort.getSortName().equals(sortTag))
                removedSort = sort;
        }
        if (removedSort == null){
            throw new Exception("You did not select this sort");
        }
        else {
            currentSorts.remove(removedSort);
            return viewAllProducts();
        }
    }
    public void addFilter(Filter filter){
        storage.addFilter(filter);
    }
}
