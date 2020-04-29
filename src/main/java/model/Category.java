package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String categoryName;
    private ArrayList<Product> thisCategoryProducts;
    private HashMap<String,String> properties;

    public Category(String categoryName, ArrayList<Product> thisCategoryProducts, HashMap<String,String> properties) {
        this.categoryName = categoryName;
        this.thisCategoryProducts = thisCategoryProducts;
        this.properties = properties;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Product> getThisCategoryProducts() {
        return thisCategoryProducts;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addToThisCategoryProducts(Product newProduct) {
        thisCategoryProducts.add(newProduct);
    }

    public void removeFromThisCategoryProducts(Product specificProduct) {
        thisCategoryProducts.removeIf(categoryProduct -> categoryProduct.equals(specificProduct));
    }

    @Override
    public String toString() {
        return "CategoryName='" + categoryName + '\'' + "\n" +
                ", thisCategoryProducts=" + thisCategoryProducts;
    }
}
