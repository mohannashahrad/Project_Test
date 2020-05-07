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

    public FileSaver(Storage storage) {
        this.storage = storage;
    }

    public void dataSaver(){
        writeArrayToFile(storage.getAllUsers(),"./dataBase/allUsers.json");
        writeArrayToFile(storage.getAllCustomers(),"./dataBase/allCustomers.json");
        writeArrayToFile(storage.getAllAdmins(),"./dataBase/allAdmins.json");
        writeArrayToFile(storage.getAllSellers(),"./dataBase/allSellers.json");
        writeArrayToFile(storage.getAllProducts(),"./dataBase/allProducts.json");
        writeArrayToFile(storage.getAllLogs(),"./dataBase/allLogs.json");
        writeArrayToFile(storage.getAllSellLogs(),"./dataBase/allSellLogs.json");
        writeArrayToFile(storage.getAllBuyLogs(),"./dataBase/buyLogs.json");
        writeArrayToFile(storage.getAllCategories(),"./dataBase/allCategories.json");
        writeArrayToFile(storage.getAllDiscounts(),"./dataBase/allDiscounts.json");
        writeArrayToFile(storage.getAllRates(),"./dataBase/allRates.json");
        writeArrayToFile(storage.getAllComments(),"./dataBase/allComments.json");
        writeArrayToFile(storage.getAllSales(),"./dataBase/allSales.json");
        writeArrayToFile(storage.getAllRequests(),"./dataBase/allRequests.json");
        writeArrayToFile(storage.getAllFilters(),"./dataBase/allFilters.json");
        writeArrayToFile(storage.getAllSorts(),"./dataBase/allSorts.json");
        writeArrayToFile(storage.getAllCarts(),"./dataBase/allCarts.json");

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
            fileWriter.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void readUser (){
        try(FileReader fileReader = new FileReader("./dataBase/allUsers.json")) {
            Person [] fromFile = gson.fromJson(fileReader,Person[].class);
            storage.setAllUsers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCustomer(){
        try(FileReader fileReader = new FileReader("./dataBase/allCustomers.json")) {
            Customer[] fromFile = gson.fromJson(fileReader,Customer[].class);
            storage.setAllCustomers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readAdmin(){
        try(FileReader fileReader = new FileReader("./dataBase/allAdmins.json")) {
            Admin[] fromFile = gson.fromJson(fileReader, Admin[].class);
            storage.setAllAdmins(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSeller(){
        try(FileReader fileReader = new FileReader("./dataBase/allSellers.json")) {
            Seller[] fromFile = gson.fromJson(fileReader, Seller[].class);
            storage.setAllSellers(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readProduct(){
        try(FileReader fileReader = new FileReader("./dataBase/allProducts.json")) {
            Product[] fromFile = gson.fromJson(fileReader,Product[].class);
            storage.setAllProducts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSale(){
        try(FileReader fileReader = new FileReader("./dataBase/allSales.json")) {
            Sale[] fromFile = gson.fromJson(fileReader,Sale[].class);
            storage.setAllSales(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCategory(){
        try(FileReader fileReader = new FileReader("./dataBase/allCategories.json")) {
            Category[] fromFile = gson.fromJson(fileReader,Category[].class);
            storage.setAllCategories(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readDiscount(){
        try(FileReader fileReader = new FileReader("./dataBase/allDiscounts.json")) {
            Discount[] fromFile = gson.fromJson(fileReader,Discount[].class);
            storage.setAllDiscounts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRate(){
        try(FileReader fileReader = new FileReader("./dataBase/allRates.json")) {
            Rate[] fromFile = gson.fromJson(fileReader,Rate[].class);
            storage.setAllRates(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readComment(){
        try(FileReader fileReader = new FileReader("./dataBase/allComments.json")) {
            Comment[] fromFile = gson.fromJson(fileReader,Comment[].class);
            storage.setAllComments(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRequest(){
        try(FileReader fileReader = new FileReader("./dataBase/allRequests.json")) {
            Request[] fromFile = gson.fromJson(fileReader,Request[].class);
            storage.setAllRequests(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readFilter(){
        try(FileReader fileReader = new FileReader("./dataBase/allFilters.json")) {
            Filter[] fromFile = gson.fromJson(fileReader,Filter[].class);
            storage.setAllFilters(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSort(){
        try(FileReader fileReader = new FileReader("./dataBase/allSorts.json")) {
            Sort[] fromFile = gson.fromJson(fileReader,Sort[].class);
            storage.setAllSorts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allLogs.json")) {
            Log[] fromFile = gson.fromJson(fileReader,Log[].class);
            storage.setAllLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readBuyLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allBuyLogs.json")) {
            BuyLog[] fromFile = gson.fromJson(fileReader,BuyLog[].class);
            storage.setAllBuyLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSellLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allSellLogs.json")) {
            SellLog[] fromFile = gson.fromJson(fileReader,SellLog[].class);
            storage.setAllSellLogs(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCart(){
        try(FileReader fileReader = new FileReader("./dataBase/allCarts.json")) {
            Cart[] fromFile = gson.fromJson(fileReader,Cart[].class);
            storage.setAllCarts(new ArrayList<>(Arrays.asList(fromFile)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
