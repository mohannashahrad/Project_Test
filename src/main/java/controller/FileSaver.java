package controller;

import com.gilecode.yagson.YaGson;
import com.google.gson.Gson;
import model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileSaver {
    private Gson gson = new File_Saver().getCustomeGson();
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
        writeArrayToFile(storage.getAllBuyLogs(),"./dataBase/allBuyLogs.json");
        writeArrayToFile(storage.getAllCategories(),"./dataBase/allCategories.json");
        writeArrayToFile(storage.getAllDiscounts(),"./dataBase/allDiscounts.json");
        writeArrayToFile(storage.getAllRates(),"./dataBase/allRates.json");
        writeArrayToFile(storage.getAllComments(),"./dataBase/allComments.json");
        writeArrayToFile(storage.getAllSales(),"./dataBase/allSales.json");
        writeArrayToFile(storage.getAllRequests(),"./dataBase/allRequests.json");

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
            Collections.addAll(storage.getAllUsers(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCustomer(){
        try(FileReader fileReader = new FileReader("./dataBase/allCustomers.json")) {
            Customer[] fromFile = gson.fromJson(fileReader,Customer[].class);
            Collections.addAll(storage.getAllCustomers(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readAdmin(){
        try(FileReader fileReader = new FileReader("./dataBase/allAdmins.json")) {
            Admin[] fromFile = gson.fromJson(fileReader, Admin[].class);
            Collections.addAll(storage.getAllAdmins(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSeller(){
        try(FileReader fileReader = new FileReader("./dataBase/allSellers.json")) {
            Seller[] fromFile = gson.fromJson(fileReader, Seller[].class);
            Collections.addAll(storage.getAllSellers(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readProduct(){
        try(FileReader fileReader = new FileReader("./dataBase/allProducts.json")) {
            Product[] fromFile = gson.fromJson(fileReader,Product[].class);
            Collections.addAll(storage.getAllProducts(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSale(){
        try(FileReader fileReader = new FileReader("./dataBase/allSales.json")) {
            Sale[] fromFile = gson.fromJson(fileReader,Sale[].class);
            Collections.addAll(storage.getAllSales(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readCategory(){
        try(FileReader fileReader = new FileReader("./dataBase/allCategories.json")) {
            Category[] fromFile = gson.fromJson(fileReader,Category[].class);
            Collections.addAll(storage.getAllCategories(),fromFile);
            boolean uncatExist = false;
            for (Category category : storage.getAllCategories()){
                if (category.getCategoryName().equals("uncategorized"))
                    uncatExist = true;
            }
            if (!uncatExist) {
                storage.getAllCategories().add(new Category("uncategorized"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readDiscount(){
        try(FileReader fileReader = new FileReader("./dataBase/allDiscounts.json")) {
            Discount[] fromFile = gson.fromJson(fileReader,Discount[].class);
            Collections.addAll(storage.getAllDiscounts(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRate(){
        try(FileReader fileReader = new FileReader("./dataBase/allRates.json")) {
            Rate[] fromFile = gson.fromJson(fileReader,Rate[].class);
            Collections.addAll(storage.getAllRates(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readComment(){
        try(FileReader fileReader = new FileReader("./dataBase/allComments.json")) {
            Comment[] fromFile = gson.fromJson(fileReader,Comment[].class);
            Collections.addAll(storage.getAllComments(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readRequest(){
        try(FileReader fileReader = new FileReader("./dataBase/allRequests.json")) {
            Request[] fromFile = gson.fromJson(fileReader,Request[].class);
            Collections.addAll(storage.getAllRequests(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allLogs.json")) {
            Log[] fromFile = gson.fromJson(fileReader,Log[].class);
            Collections.addAll(storage.getAllLogs(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readBuyLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allBuyLogs.json")) {
            BuyLog[] fromFile = gson.fromJson(fileReader,BuyLog[].class);
            Collections.addAll(storage.getAllBuyLogs(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void readSellLog(){
        try(FileReader fileReader = new FileReader("./dataBase/allSellLogs.json")) {
            SellLog[] fromFile = gson.fromJson(fileReader,SellLog[].class);
            Collections.addAll(storage.getAllSellLogs(),fromFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
