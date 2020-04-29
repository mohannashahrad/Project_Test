package model;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    private String sortName;

    public Sort(String sortName) {
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }

    public void sortByCategory(ArrayList<Category> allCategories) {
        ArrayList<Product> sortedProducts = new ArrayList<>();
        for (Category category : allCategories) {
            /**/
        }
    }

    public ArrayList<Product> sortByPrice(ArrayList<Product> allProducts) {
        for (int i = 0; i < allProducts.size() - 1; i++) {
            for (int j = 0; j < allProducts.size() - 1; j++) {
                if (allProducts.get(j).compareTo(allProducts.get(j + 1)) > 0) {
                    Collections.swap(allProducts, j, j + 1);
                }
            }
        }
        return allProducts;
    }
}
