package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.*;

public class SellerManager extends Manager {
    public SellerManager() {
    }

    public void registerSeller(HashMap<String,String> information){
        storage.addRequest(new Request("register seller" , information));
    }
}
