package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Person{
    private ArrayList<Request> allRequests;

    public Admin(HashMap<String, String> information) {
        super(information);
        this.allRequests = new ArrayList<>();
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }
}

