package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class SellerManager extends Manager {

    private HashMap<Integer, ArrayList<Product>> savedProductsInSale = new HashMap<>();

    public SellerManager() {
    }

    public HashMap<Integer, ArrayList<Product>> getSavedProductsInSale() {
        return savedProductsInSale;
    }

    public Product viewSellerProduct(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("A product with this Id does noe exist!!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have this product!!");
        else
            return storage.getProductById(productId);
    }

    public void addProduct(HashMap<String, String> information){
        information.put("seller", super.person.getUsername());
        storage.addRequest(new Request("add product", information));
    }

    public void removeProduct(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else {
            HashMap<String, String> information = new HashMap<>();
            information.put("productId", Integer.toString(productId));
            information.put("username", super.person.getUsername());
            storage.addRequest(new Request("remove product", information));
        }
    }

    public ArrayList<Customer> viewProductBuyers(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else
            return storage.getProductById(productId).getThisProductsBuyers();
    }

    public void editProduct(int productId, String field, String updatedVersion) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product!");
        else if (storage.getProductById(productId).getSeller() != super.person)
            throw new Exception("You don't have such product!");
        else {
            HashMap<String, String> information = new HashMap<>();
            information.put(field, updatedVersion);
            information.put("productId", Integer.toString(productId));
            storage.addRequest(new Request("edit product", information));
        }
    }

    public ArrayList<Sale> viewSellerOffs() {
        return ((Seller) super.person).getSaleList();
    }

    public ArrayList<Product> viewSellerProducts() {
        return ((Seller) super.person).getProductsToSell();
    }

    public void addOff(HashMap<String, String> information, ArrayList<Product> productsInOff) throws Exception {
        Sale sale = null;
        int offId = sale.getLastSaleId() + 1;
        savedProductsInSale.put(offId, productsInOff);
        information.put("username", super.person.getUsername());
        information.put("offId", Integer.toString(offId));
        storage.addRequest(new Request("add sale", information));
    }

    public void editOff(int offId, String field, String updatedVersion) throws Exception {
        if (storage.getSaleById(offId) == null)
            throw new Exception("There is not such off!");
        else if (!((Seller) super.person).getSaleList().contains(storage.getSaleById(offId)))
            throw new Exception("You don't have such off!");
        else {
            HashMap<String, String> information = new HashMap<>();
            information.put(field, updatedVersion);
            information.put("offId", Integer.toString(offId));
            storage.addRequest(new Request("edit off", information));
        }
    }

    public boolean doesSellerHaveProduct(int productId) {
        if (storage.getProductById(productId).getSeller().equals(super.person)) return true;
        else return false;
    }

    public boolean doesProductExist(int productId) {
        if (storage.getProductById(productId) == null)
            return false;
        else return true;
    }

    public Product getSellerProductById(int productId) {
        return storage.getProductById(productId);
    }

    public boolean doesSellerHaveThisOff(int offId) {
        if (((Seller) super.person).getSaleList().contains(storage.getSaleById(offId)))
            return true;
        else return false;
    }

    public void addProductToOff(int offId, int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is no product with this Id!");
        else if (storage.getSaleById(offId) == null)
            throw new Exception("There is no off with this Id");
        else if (!((Seller) super.person).getSaleList().contains(storage.getSaleById(offId)))
            throw new Exception("You don't have this odd");
        else if (storage.getSaleById(offId).getProductsWithThisSale().contains(storage.getProductById(productId)))
            throw new Exception("This product is already added in this sale!");
        else {
            HashMap<String, String> information = new HashMap<>();
            information.put("offId", Integer.toString(offId));
            information.put("productId", Integer.toString(productId));
            storage.addRequest(new Request("add product to sale", information));
        }
    }

    public void removeProductFromOff(int offId, int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is no product with this Id!");
        else if (storage.getSaleById(offId) == null)
            throw new Exception("There is no off with this Id");
        else if (!((Seller) super.person).getSaleList().contains(storage.getSaleById(offId)))
            throw new Exception("You don't have this odd");
        else if (!storage.getSaleById(offId).getProductsWithThisSale().contains(storage.getProductById(productId)))
            throw new Exception("This product is not assigned to this sale!");
        else {
            HashMap<String, String> information = new HashMap<>();
            information.put("offId", Integer.toString(offId));
            information.put("productId", Integer.toString(productId));
            storage.addRequest(new Request("remove product from sale", information));
        }
    }

    public Sale viewSingleOff(int offId) throws Exception {
        if (storage.getSaleById(offId) == null)
            throw new Exception("There is not such off!");
        else if (!((Seller) super.person).getSaleList().contains(storage.getSaleById(offId)))
            throw new Exception("You don't have this off!");
        else
            return storage.getSaleById(offId);
    }
}

