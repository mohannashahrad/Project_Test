package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import model.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class JacksonSaver {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
    private Storage storage = Storage.getStorage();
    public void dataSaver(){
        objectMapper.enableDefaultTyping();
        try {
            writer.writeValue(new File("./dataBase/allUsers.json"),storage.getAllUsers());
          //  writer.writeValue(new File("./dataBase/allAdmins.json"),storage.getAllAdmins());
           // writer.writeValue(new File("./dataBase/allRequests.json"),storage.getAllRequests());

           /* writer.writeValue(new File("./dataBase/allUsers.json"),storage.getAllUsers());
            writer.writeValue(new File("./dataBase/allSellers.json"),storage.getAllSellers());
            writer.writeValue(new File("./dataBase/allCustomers.json"),storage.getAllCustomers());
            writer.writeValue(new File("./dataBase/allAdmins.json"),storage.getAllAdmins());
            writer.writeValue(new File("./dataBase/allLogs.json"),storage.getAllLogs());
            writer.writeValue(new File("./dataBase/allSellLogs.json"),storage.getAllSellLogs());
            writer.writeValue(new File("./dataBase/allBuyLogs.json"),storage.getAllBuyLogs());
            writer.writeValue(new File("./dataBase/allProducts.json"),storage.getAllProducts());
            writer.writeValue(new File("./dataBase/allCategories.json"),storage.getAllCategories());
            writer.writeValue(new File("./dataBase/allDiscounts.json"),storage.getAllDiscounts());
            writer.writeValue(new File("./dataBase/allRates.json"),storage.getAllRates());
            writer.writeValue(new File("./dataBase/allComments.json"),storage.getAllComments());
            writer.writeValue(new File("./dataBase/allSales.json"),storage.getAllSales());
            writer.writeValue(new File("./dataBase/allRequests.json"),storage.getAllRequests());*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void dataReader(){
        try {

            File file = new File("./dataBase/allUsers.json");
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return;
            }
            Person[] temp = objectMapper.readValue(file, Person[].class);
            Collections.addAll(storage.getAllUsers(),temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
