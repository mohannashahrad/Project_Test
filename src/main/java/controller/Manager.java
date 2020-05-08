package controller;

import java.util.ArrayList;

import View.Menu;
import model.*;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    protected Storage storage;
    protected static Person person;
    protected Cart cart = null;
    protected SellerManager sellerManager;
    protected ArrayList<Filter> currentFilters = new ArrayList<>();
    protected ArrayList<Sort> currentSorts = new ArrayList<>();
    protected ArrayList<Product> filteredProducts = new ArrayList<>();



    public Manager() {
        storage = Storage.getStorage();
        cart = Cart.getCart();
        storage.addCart(cart);

    }

    public void register(HashMap<String, String> information) throws Exception {
        if (!checkValidity(information.get("username")))
            throw new Exception("Username is not valid");
        else if (!checkValidity(information.get("password")))
            throw new Exception("Password is not valid");
        else if (!checkEmailValidity(information.get("email")))
            throw new Exception("Email is not valid");
        else if (!checkPhoneNumberValidity(information.get("number")))
            throw new Exception("Phone Number is not valid");
        else if (information.get("role").equalsIgnoreCase("seller"))
            storage.addRequest(new Request("register seller", information));
        else if (information.get("role").equalsIgnoreCase("customer"))
            storage.addUser(new Customer(information));
        else if (information.get("role").equalsIgnoreCase("admin"))
            storage.addUser(new Admin(information));
    }

    public Person login(String username, String password) throws Exception {
        if (!checkValidity(username))
            throw new Exception("Username is not valid");
        else if (!checkValidity(password))
            throw new Exception("Password is not valid");
        else if (!storage.getUserByUsername(username).getPassword().equals(password))
            throw new Exception("Your password is wrong");
        else {
            this.person = storage.getUserByUsername(username);
            return storage.getUserByUsername(username);
        }
    }

    public void logout() {
        Menu.setPerson(null);
        this.person = null;
        this.cart = null;
    }

    public void editField(String field, String updatedVersion) throws Exception {
        if (field.equals("password")) {
            if (!checkValidity(updatedVersion))
                throw new Exception("Password is not valid");
            else
                this.person.setPassword(updatedVersion);
        } else if (field.equals("name"))
            this.person.setName(updatedVersion);
        else if (field.equals("familyName"))
            this.person.setFamilyName(updatedVersion);
        else if (field.equals("email")) {
            if (!checkEmailValidity(updatedVersion))
                throw new Exception("Email is not valid");
            else
                this.person.setEmail(updatedVersion);
        } else if (field.equals("number")) {
            if (!checkPhoneNumberValidity(updatedVersion))
                throw new Exception("Phone Number is not valid");
            else
                this.person.setNumber(updatedVersion);
        }
    }

    public boolean checkValidity(String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean checkEmailValidity(String input) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean checkPhoneNumberValidity(String input) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean doesUsernameExist(String username) {
        if (storage.getUserByUsername(username) == null)
            return false;
        else return true;
    }

    public boolean doesDiscountExist(String code) {
        if (storage.getDiscountByCode(code) == null)
            return false;
        else return true;
    }

    public ArrayList<Category> viewAllCategories() {
        return storage.getAllCategories();
    }

    public Category viewCategory(String name) throws Exception {
        if (storage.getCategoryByName(name) == null)
            throw new Exception("There is not such category");
        else
            return storage.getCategoryByName(name);
    }

    public ArrayList<Sort> viewAllSorts() {
        return storage.getAllSorts();
    }

    public ArrayList<Sale> viewAllOffs() {
        return storage.getAllSales();
    }

    public Product viewProduct(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("There is not such product");
        else
            return storage.getProductById(productId);
    }

    public boolean doesPersonBoughtProduct(Person person, int productId) {
        for (Person buyer : storage.getProductById(productId).getThisProductsBuyers()) {
            if (buyer.equals(person))
                return true;
        }
        return false;
    }

    public Product getProductById(int productId) throws Exception {
        if (storage.getProductById(productId) == null)
            throw new Exception("Product doesn't exist!");
        else
            return storage.getProductById(productId);
    }

    public void terminate() {
        FileSaver fileSaver = new FileSaver();
        fileSaver.dataSaver();
    }

    public boolean doesCategoryExist(String name) {
        if (storage.getCategoryByName(name) == null)
            return false;
        else return true;
    }

}
