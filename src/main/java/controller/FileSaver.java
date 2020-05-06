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
        readLog();
        readBuyLog();
        readSellLog();
        readCategory();
        readDiscount();
        readRate();
        readComment();
        readSale();
        readRequest();
        readFilter();
        readSort();
        //readCart();
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
    private void readSale(){
        try(FileReader fileReader = new FileReader("allSales.json")) {
            Sale[] fromFile = gson.fromJson(fileReader,Sale[].class);
            storage.setAllSales(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCategory(){
        try(FileReader fileReader = new FileReader("allCategories.json")) {
            Category[] fromFile = gson.fromJson(fileReader,Category[].class);
            storage.setAllCategories(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readDiscount(){
        try(FileReader fileReader = new FileReader("allDiscounts.json")) {
            Discount[] fromFile = gson.fromJson(fileReader,Discount[].class);
            storage.setAllDiscounts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRate(){
        try(FileReader fileReader = new FileReader("allRates.json")) {
            Rate[] fromFile = gson.fromJson(fileReader,Rate[].class);
            storage.setAllRates(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readComment(){
        try(FileReader fileReader = new FileReader("allComments.json")) {
            Comment[] fromFile = gson.fromJson(fileReader,Comment[].class);
            storage.setAllComments(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRequest(){
        try(FileReader fileReader = new FileReader("allRequests.json")) {
            Request[] fromFile = gson.fromJson(fileReader,Request[].class);
            storage.setAllRequests(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readFilter(){
        try(FileReader fileReader = new FileReader("allFilters.json")) {
            Filter[] fromFile = gson.fromJson(fileReader,Filter[].class);
            storage.setAllFilters(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSort(){
        try(FileReader fileReader = new FileReader("allSorts.json")) {
            Sort[] fromFile = gson.fromJson(fileReader,Sort[].class);
            storage.setAllSorts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readLog(){
        try(FileReader fileReader = new FileReader("allLogs.json")) {
            Log[] fromFile = gson.fromJson(fileReader,Log[].class);
            storage.setAllLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readBuyLog(){
        try(FileReader fileReader = new FileReader("allBuyLogs.json")) {
            BuyLog[] fromFile = gson.fromJson(fileReader,BuyLog[].class);
            storage.setAllBuyLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSellLog(){
        try(FileReader fileReader = new FileReader("allSellLogs.json")) {
            SellLog[] fromFile = gson.fromJson(fileReader,SellLog[].class);
            storage.setAllSellLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCart(){
        try(FileReader fileReader = new FileReader("allCarts.json")) {
            Cart[] fromFile = gson.fromJson(fileReader,Cart[].class);
            storage.setAllCarts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
