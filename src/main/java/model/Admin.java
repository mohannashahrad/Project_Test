package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Person{
    private ArrayList<Request> allRequests;
    private static ArrayList<Person> allAdmins = new ArrayList<>();

    public static ArrayList<Person> getAllAdmins() {
        return allAdmins;
    }

    public Admin(HashMap<String, String> information) {
        super(information);
        this.allRequests = new ArrayList<>();
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }
}

