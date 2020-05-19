package controller;

import com.google.gson.*;
import model.Admin;
import model.Person;

import java.lang.reflect.Type;

public class File_Saver {
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson getCustomeGson(){
        gsonBuilder.registerTypeAdapter(Admin.class,new AdminSerializer());
        Gson customGson = gsonBuilder.create();
        return customGson;
    }
    public String test(Admin admin){
        return getCustomeGson().toJson(admin);
    }



}
class AdminSerializer implements JsonSerializer<Admin> {
    Gson gsonDefault = new Gson();
    @Override
    public JsonElement serialize(Admin admin, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonElement = gsonDefault.toJsonTree(admin);
        JsonElement allRequests = gsonDefault.toJsonTree(admin.getAllRequestIds());
        jsonElement.getAsJsonObject().add("allRequests",allRequests);
        return jsonElement;
    }
}
