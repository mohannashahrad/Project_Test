package model;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<Product> thisCategoryProducts;

    public Category(String categoryName, ArrayList<Product> thisCategoryProducts) {
        this.categoryName = categoryName;
        this.thisCategoryProducts = thisCategoryProducts;
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
