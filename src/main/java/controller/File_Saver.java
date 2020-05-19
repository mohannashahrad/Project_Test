package controller;

import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class File_Saver {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson getCustomeGson(){
        gsonBuilder.registerTypeAdapter(Admin.class,new AdminSerializer());
        gsonBuilder.registerTypeAdapter(Seller.class,new SellerSerializer());
        Gson customGson = gsonBuilder.create();
        return customGson;
    }
    public String test(Seller seller){
        return getCustomeGson().toJson(seller);
    }



}
class AdminSerializer implements JsonSerializer<Admin> {
    Gson gsonDefault = new Gson();
    private ArrayList<Integer> getAllRequestIds(ArrayList<Request> allRequestIds){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Request request : allRequestIds){
            temp.add(request.getRequestId());
        }
        return temp;
    }
    @Override
    public JsonElement serialize(Admin admin, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonElement = gsonDefault.toJsonTree(admin);
        JsonElement allRequests = gsonDefault.toJsonTree(getAllRequestIds(admin.getAllRequests()));
        jsonElement.getAsJsonObject().add("allRequests",allRequests);
        return jsonElement;
    }
}
class SellerSerializer implements JsonSerializer<Seller> {
    Gson gsonDefault = new Gson();
    private ArrayList<Integer> getAllProductId(ArrayList<Product> productsToSell){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Product product : productsToSell){
            temp.add(product.getProductId());
        }
        return temp;
    }
    private ArrayList<Integer> getAllSaleId(ArrayList<Sale> saleList){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Sale sale : saleList){
            temp.add(sale.getSaleId());
        }
        return temp;
    }

    @Override
    public JsonElement serialize(Seller seller, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonElement = gsonDefault.toJsonTree(seller);
        JsonElement products = gsonDefault.toJsonTree(getAllProductId(seller.getProductsToSell()));
        JsonElement sales = gsonDefault.toJsonTree(getAllSaleId(seller.getSaleList()));
        jsonElement.getAsJsonObject().add("productsToSell",products);
        jsonElement.getAsJsonObject().add("saleList",sales);
        return jsonElement;
    }
}
class CustomerSerializer implements JsonSerializer<Customer> {
    Gson gsonDefault = new Gson();
    private ArrayList<Integer> getAllDiscountId(ArrayList<Discount> allDiscounts){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Discount discount : allDiscounts){
            temp.add(discount.c);
        }
        return temp;
    }
    private ArrayList<Integer> getAllBuyLogId(ArrayList<BuyLog> buyHistory){
        ArrayList<Integer> temp = new ArrayList<>();
        for (BuyLog buyLog : buyHistory){
            temp.add(buyLog.getBuyCode());
        }
        return temp;
    }

    @Override
    public JsonElement serialize(Seller seller, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonElement = gsonDefault.toJsonTree(seller);
        JsonElement products = gsonDefault.toJsonTree(getAllProductId(seller.getProductsToSell()));
        JsonElement sales = gsonDefault.toJsonTree(getAllSaleId(seller.getSaleList()));
        jsonElement.getAsJsonObject().add("productsToSell",products);
        jsonElement.getAsJsonObject().add("saleList",sales);
        return jsonElement;
    }
}

