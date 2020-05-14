package model;

import java.util.ArrayList;

public class Filter {
    private String filterName;
    private String filterInfo;
    private static ArrayList<Filter> allFilters = new ArrayList<>();



    public Filter(String filterName , String filterInfo) {
        this.filterName = filterName;
        this.filterInfo = filterInfo;
        allFilters.add(this);
    }

    public String getFilterName() {
        return filterName;
    }

    public String getFilterInfo() {
        return filterInfo;
    }

    public static ArrayList<Filter> getAllFilters() {
        return allFilters;
    }
    public void setFilterInfo(String filterInfo) {
        this.filterInfo = filterInfo;
    }

    public ArrayList<Product> filterByCategory(Category category, ArrayList<Product> allProducts){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategoryName().equals(category.getCategoryName())){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    public ArrayList<Product> filterByPrice(double price, ArrayList<Product> allProducts){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if(product.getPrice() <= price){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    public ArrayList<Product> filterByName(String name, ArrayList<Product> allProducts){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if(product.getName().equals(name)){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public String toString() {
        return "Filter by :" + filterName ;
    }

    @Override
    public boolean equals(Object obj) {
        Filter first = (Filter) this;
        Filter second = (Filter) obj;
        boolean info = first.getFilterInfo().equals(second.getFilterInfo());
        boolean name = first.getFilterName().equals(second.getFilterName());
        return (info && name);
    }
}
