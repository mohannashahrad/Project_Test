package model;

import java.util.ArrayList;

public class Filter {
    private String filterName;
    public Filter(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
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
}
