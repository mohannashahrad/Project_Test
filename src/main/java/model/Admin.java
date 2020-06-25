package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Admin extends Person{
    private int id;
    private ArrayList<Request> allRequests;
    private static ArrayList<Person> allAdmins = new ArrayList<>();

    public static ArrayList<Person> getAllAdmins() {
        return allAdmins;
    }

    public Admin(HashMap<String, String> information) {
        super(information);
        this.allRequests = new ArrayList<>();
        this.id = idSetter();
    }
    private int idSetter() {
        if (allAdmins.size() == 0) {
            return 1;
        }
        int max = 0;
        for (Person person : allAdmins) {
            if (((Admin)person).id > max)
                max = ((Admin)person).id;
        }
        return max + 1;
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }

}


