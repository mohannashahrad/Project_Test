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

    public ArrayList<Product> sortByAverageRate(ArrayList<Product> allProducts) {
        ArrayList<Product> sortedProducts = allProducts;
        for (int i = 0; i < sortedProducts.size() - 1 ; i++) {
            for (int j = 0; j < sortedProducts.size() - 1; j++) {
                if (sortedProducts.get(j).getAverageRate() > sortedProducts.get(j + 1).getAverageRate()) {
                    Collections.swap(sortedProducts, j , j + 1);
                }
            }
        }
        return sortedProducts;
    }

    public ArrayList<Product> sortByPrice(ArrayList<Product> allProducts) {
        ArrayList<Product> sortedProducts = allProducts;
        for (int i = 0; i < sortedProducts.size() - 1; i++) {
            for (int j = 0; j < sortedProducts.size() - 1; j++) {
                if (sortedProducts.get(j).compareTo(sortedProducts.get(j + 1)) > 0) {
                    Collections.swap(sortedProducts, j, j + 1);
                }
            }
        }
        return sortedProducts;
    }

    public ArrayList<Product> defaultSort(ArrayList<Product> allProducts){
        ArrayList<Product> sortedProducts = allProducts;
        for (int i = 0; i < sortedProducts.size() - 1; i++) {
            for (int j = 0; j < sortedProducts.size() - 1; j++) {
                if (sortedProducts.get(j).getNumberOfSeen() < sortedProducts.get(j + 1).getNumberOfSeen()) {
                    Collections.swap(sortedProducts, j, j + 1);
                }
            }
        }
        return sortedProducts;
    }
}
