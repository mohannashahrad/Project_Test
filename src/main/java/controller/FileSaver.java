package controller;

import com.google.gson.Gson;
import model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class FileSaver {
    private Gson gson = new Gson();
    private Storage storage;

    public void dataSaver(){
        writeArrayToFile(storage.getAllUsers(),"allUsers.json");
        writeArrayToFile(storage.getAllCustomers(),"allCustomers.json");
        writeArrayToFile(storage.getAllAdmins(),"allAdmins.json");
        writeArrayToFile(storage.getAllSellers(),"allSellers.json");
        writeArrayToFile(storage.getAllProducts(),"allProducts.json");
        writeArrayToFile(storage.getAllLogs(),"allLogs.json");
        writeArrayToFile(storage.getAllSellLogs(),"allSellLogs.json");
        writeArrayToFile(storage.getAllBuyLogs(),"buyLogs.json");
        writeArrayToFile(storage.getAllCategories(),"allCategories.json");
        writeArrayToFile(storage.getAllDiscounts(),"allDiscounts.json");
        writeArrayToFile(storage.getAllRates(),"allRates.json");
        writeArrayToFile(storage.getAllComments(),"allComments.json");
        writeArrayToFile(storage.getAllSales(),"allSales.json");
        writeArrayToFile(storage.getAllRequests(),"allRequests.json");
        writeArrayToFile(storage.getAllFilters(),"allFilters.json");
        writeArrayToFile(storage.getAllSorts(),"allSorts.json");
        writeArrayToFile(storage.getAllCarts(),"allCarts.json");

    }
    public void dataReader(){
        readUser();
        readCustomer();
        readAdmin();
        readSeller();
        readProduct();
    }

    private void writeArrayToFile(ArrayList arrayList,String filePath){
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(arrayList,fileWriter);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void readUser (){
        try(FileReader fileReader = new FileReader("allUsers.json")) {
            Person [] fromFile = gson.fromJson(fileReader,Person[].class);
            storage.setAllUsers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCustomer(){
        try(FileReader fileReader = new FileReader("allCustomers.json")) {
            Customer[] fromFile = gson.fromJson(fileReader,Customer[].class);
            storage.setAllCustomers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readAdmin(){
        try(FileReader fileReader = new FileReader("allAdmins.json")) {
            Admin[] fromFile = gson.fromJson(fileReader, Admin[].class);
            storage.setAllAdmins(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSeller(){
        try(FileReader fileReader = new FileReader("allSellers.json")) {
            Seller[] fromFile = gson.fromJson(fileReader, Seller[].class);
            storage.setAllSellers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readProduct(){
        try(FileReader fileReader = new FileReader("allProducts.json")) {
            Product[] fromFile = gson.fromJson(fileReader,Product[].class);
            storage.setAllProducts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //and other reads...
}
