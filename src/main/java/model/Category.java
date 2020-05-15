package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String categoryName;
    private ArrayList<Product> thisCategoryProducts;
    private HashMap<String, String> properties;
    private static ArrayList<Category>allCategories = new ArrayList<>();


    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }
    public static boolean doesCategoryExist(String name){
        for (Category category : allCategories){
            if (category.categoryName.equals(name))
                return true;
        }
        return false;
    }

    public static void deleteUncategorized() {
       if (doesCategoryExist("uncategorized"))
            allCategories.remove(Category.getCategoryByName("uncategorized"));
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.thisCategoryProducts = new ArrayList<>();
        this.properties = new HashMap<>();
    }
    public void addProductToCategory(Product product){
        this.thisCategoryProducts.add(product);
    }
    public static Category getCategoryByName(String name){
        for (Category category : allCategories){
            if (category.getCategoryName().equals(name))
                return category;
        }
        return null;
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

    public void setSingleValueInProperties(String property , String newValue){
        this.properties.replace(property,newValue);
    }

    public void addNewProperty (String property , String value){
        this.properties.put(property,value);
    }

    public void addToThisCategoryProducts(Product newProduct) {
        thisCategoryProducts.add(newProduct);
    }

    public void removeFromThisCategoryProducts(Product specificProduct) {
        thisCategoryProducts.removeIf(categoryProduct -> categoryProduct.equals(specificProduct));
    }

    public void addToProperties(String key, String value) {
        this.properties.put(key, value);
    }

    public void removeProperty(String key, String value) {
        this.properties.remove(key, value);
    }

    @Override
    public String toString() {
        return "CategoryName='" + categoryName + '\'' + "\n" +
                ", thisCategoryProducts=" + thisCategoryProducts;
    }
}
