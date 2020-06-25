package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.HashMap;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Customer extends Person {
    private int id;
    private ArrayList<BuyLog> buyHistory;
    private ArrayList<Discount> allDiscounts;
    private double amountOfAllPurchasing;
    private static ArrayList<Person> allCustomers = new ArrayList<>();

    public static ArrayList<Person> getAllCustomers() {
        return allCustomers;
    }
    @JsonCreator
    public Customer(HashMap<String, String> information) {
        super(information);
        this.buyHistory = new ArrayList<>();
        this.allDiscounts = new ArrayList<>();
        this.id = idSetter();
    }
    private int idSetter() {
        if (allCustomers.size() == 0) {
            return 1;
        }
        int max = 0;
        for (Person person : allCustomers) {
            if (((Customer)person).id > max)
                max = ((Customer)person).id;
        }
        return max + 1;
    }

    public ArrayList<BuyLog> getBuyHistory() {
        return buyHistory;
    }

    public ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public void addToAllDiscounts(Discount newDiscount) {
        this.allDiscounts.add(newDiscount);
    }

    public void addToBuyLogs(BuyLog newBuyLog) {
        buyHistory.add(newBuyLog);
    }

    public void addAmountOfAllPurchasing(double amountToBeAdded){
        this.amountOfAllPurchasing += amountToBeAdded;
    }

    public void setAmountOfAllPurchasing(double amountOfAllPurchasing) {
        this.amountOfAllPurchasing = amountOfAllPurchasing;
    }

    public double getAmountOfAllPurchasing() {
        return amountOfAllPurchasing;
    }
}
