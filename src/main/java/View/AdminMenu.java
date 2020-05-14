package View;

import model.*;
import controller.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminMenu extends AccountMenu{
    AdminManager adminManager;

    public AdminMenu(Menu previousMenu) {
        super("AdminMenu", previousMenu);
        adminManager = new AdminManager();
    }

    @Override
    public void commandProcess() {
        do {
            show();
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                viewInfo();
            else if (command.equals("2"))
                manageUsers();
            else if (command.equals("3"))
                manageAllProducts();
            else if (command.equals("4")) {
                createDiscountCode();
            } else if (command.equals("5"))
                viewDiscountCodes();
            else if (command.equals("6"))
                manageRequestsMenu();
            else if (command.equals("7"))
                manageCategoriesMenu();
            else if (command.equals("8")){
                LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu(this);
                loginRegisterMenu.run();
            }
            else if (command.equals("9"))
                break;
            else if (command.equalsIgnoreCase("help"))
                show();
            else
                System.out.println("Invalid choice");


        }
        while (true);
        this.getPreviousMenu().run();
    }

    private void manageCategoriesMenu() {
        viewAllCategories();
        while (true){
            System.out.println("Enter\n1.edit a category\n2.add a category\n3.remove a category\n4.back :");
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                editCategory();
            else if (command.equals("2"))
                addCategory();
            else if (command.equals(3))
                removeCategory();
            else if (command.equals("4"))
                break;
            else
                System.out.println("Invalid choice");
        }
    }

    private void removeCategory() {
        System.out.println("Enter category's name :");
        String name = scanner.nextLine();
        try {
            adminManager.removeCategory(name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addCategory() {
        System.out.println("Enter category's name :");
        String name = scanner.nextLine();
        if (adminManager.doesCategoryExist(name)){
            System.out.println("category with this name already exists.");
            return;
        }
        try {
            adminManager.addCategory(name);
            System.out.println("category created!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void editCategory() {
        System.out.println("Enter category name :");
        String name = scanner.nextLine().trim();
        try {
            Category category = adminManager.viewCategory(name);
            viewSingleCategory(category);
            while (true){
                System.out.println("Enter\n1.edit name\n2.edit propeties\n3.back :");
                String command = scanner.nextLine().trim();
                if (command.equals("1")){
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    adminManager.editCategoryByName(name,newName);
                    System.out.println("edited successfully!");
                }
                else if (command.equals("2")){
                    System.out.println("Enter property's name :");
                    String property = scanner.nextLine();
                    System.out.println("Enter property's value :");
                    String newValue = scanner.nextLine();
                    adminManager.editCategoryByProperties(category,property,newValue);
                    System.out.println("edited successfully!");
                }

                else if (command.equals("3"))
                    break;
                else
                    System.out.println("Invalid choice");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private void viewSingleCategory(Category category) {
        System.out.println("name : "+category.getCategoryName());
        System.out.println("products in this category : ");
        ArrayList<Product> products = category.getThisCategoryProducts();
        if (((ArrayList) products).isEmpty()){
            System.out.println("no products in this category yet.");
        }else {
            for (Product product : products){
                System.out.println(product.getName()+"***"+product.getProductId());
            }
        }
        System.out.println(category.getProperties());


    }

    private void viewAllCategories() {
        ArrayList<Category> allCategories = adminManager.viewAllCategories();
        if (allCategories.isEmpty()){
            System.out.println("no categories yet.");
            return;
        }

        for (Category category : allCategories){
            System.out.println(category.getCategoryName());
        }
    }

    private void manageRequestsMenu() {
        ArrayList<Request> allRequests = adminManager.viewAllRequests();
        showAllRequests(allRequests);
        while (true){
            System.out.println("Enter request id or 'back' to return :");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("back"))
                break;
            else {
                try {
                    Request request = adminManager.viewRequest(command);
                    ShowSingleRequest(request);
                    System.out.println("Enter\n1.accept\n2.decline\n3.take no action");
                    int choice =Integer.parseInt(scanner.nextLine());
                    if (choice == 1){
                        adminManager.acceptRequest(command);
                        System.out.println("accepted!");
                    }


                    else if (choice == 2){
                        adminManager.declineRequest(command);
                        System.out.println("declined!");
                    }


                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        }


    }

    private void ShowSingleRequest(Request request) {
        System.out.println("id : "+request.getRequestId());
        System.out.println("type of request : "+request.getTypeOfRequest());
        System.out.println("state : "+request.getStateOfRequest());
        System.out.println("information : ");
        System.out.println(request.getInformation());
    }

    private void showAllRequests(ArrayList<Request> allRequests) {
        if (allRequests.isEmpty()){
            System.out.println("no requests yet.");
            return;
        }
        for (Request request: allRequests) {
            System.out.println("request id : "+request.getRequestId()+"   state : "+request.getStateOfRequest());
            System.out.println("-------");
        }

    }

    private void viewDiscountCodes() {
        boolean anythingShowed = viewAllDiscount();
        if (anythingShowed == false)
            return;
        while (true) {
            System.out.println("Enter\n1.view a DiscountCode\n2.edit a DiscountCode\n3.remove a DiscountCode\n4.back");
            String command = scanner.nextLine().trim();
            if (command.equals("1"))
                showSingleDiscountMenu();
            else if (command.equals("2"))
                editDiscountMenu();
            else if (command.equals(3))
                removeDiscount();
            else if (command.equals("4"))
                break;
            else
                System.out.println("Invalid choice");
        }
    }

    private void removeDiscount() {
        System.out.println("Enter discount code");
        String code = scanner.nextLine().trim();
        try {
            adminManager.removeDiscountCode(code);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void editDiscountMenu() {
        System.out.println("Enter discount code :");
        String code = scanner.nextLine().trim();
        if (!adminManager.doesDiscountExist(code)) {
            System.out.println("Invalid code.");
            return;
        }
        Discount discount = adminManager.viewDiscountCode(code);
        while (true) {
            System.out.println("Enter field to edit :\n1.start date and time\n2.end date and time\n3.percentage\n4.maximum amount" +
                    "\n5.usage per customer\n6.add customer to this discount\n7.remove customer from this discount\n8.back");
            int command = Integer.parseInt(scanner.nextLine());
            if (!(0 < command && command < 9)) {
                System.out.println("Invalid command");
                continue;
            } else if (command == 8)
                break;

            if (command == 1) {
                System.out.println("Enter new value :");
                String newValue = scanner.nextLine();
                try {
                    adminManager.editDiscountField(discount, "beginDate", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command == 2) {
                System.out.println("Enter new value :");
                String newValue = scanner.nextLine();
                try {
                    adminManager.editDiscountField(discount, "endDate", newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command == 3) {
                System.out.println("Enter new value :");
                String newValue = scanner.nextLine();
                adminManager.editDiscountField(discount, "percentage", newValue);
            }
            else if (command == 4) {
                System.out.println("Enter new value :");
                String newValue = scanner.nextLine();
                adminManager.editDiscountField(discount, "maxAmount", newValue);
            }
            else if (command == 5) {
                System.out.println("Enter new value :");
                String newValue = scanner.nextLine();
                adminManager.editDiscountField(discount, "usagePerCustomer", newValue);
            }
            else if (command == 6) {
                System.out.println("Enter customer's username :");
                String username = scanner.nextLine().trim();
                try {
                    adminManager.addCustomerToDiscount(username,discount);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command == 7) {
                System.out.println("Enter customer's username :");
                String username = scanner.nextLine().trim();
                try {
                    adminManager.removeCustomerFromDiscount(discount, username);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }


    private boolean viewAllDiscount() {
        ArrayList<Discount> allCodes = adminManager.viewAllDiscountCodes();
        if (allCodes.isEmpty()){
            System.out.println("no discount codes yet.");
            return false;
        }
        for (Discount discount : allCodes) {
            System.out.println(discount.getDiscountCode());
            System.out.println("-------------------------");
        }
        return true;
    }

    private void showSingleDiscountMenu() {
        System.out.println("Enter discount code :");
        String code = scanner.nextLine().trim();
        if (!adminManager.doesDiscountExist(code)) {
            System.out.println("Invalid code.");
            return;
        }
        Discount discount = adminManager.viewDiscountCode(code);
        showSingleDiscount(discount);
    }

    private void showSingleDiscount(Discount discount) {
        System.out.println("code : " + discount.getDiscountCode());
        System.out.println("start date and time : " + discount.getBeginDate());
        System.out.println("end date and time : " + discount.getEndDate());
        System.out.println("percentage : " + discount.getPercentage() + "%");
        System.out.println("maximum amount : " + discount.getMaxAmount());
        System.out.println("usage per customer : " + discount.getUsageCount());
        System.out.println("customer with this discount --- code usage number");
        HashMap<Customer, Integer> customersWithThisDiscount = discount.getCustomersWithThisDiscount();
        if (customersWithThisDiscount.isEmpty()) {
            System.out.println("No customer added yet.");
            return;
        }
        for (Map.Entry<Customer, Integer> entry : customersWithThisDiscount.entrySet()) {
            System.out.println(entry.getKey().getUsername() + " --- " + entry.getValue());
        }
    }

    private void createDiscountCode() {

        System.out.println("Enter discountCode :");
        String code = scanner.nextLine().trim();
        System.out.println("start date and time (yyyy,MM,dd,HH,mm) :");
        String startDate = scanner.nextLine().trim();

        System.out.println("end date and time (yyyy,MM,dd,HH,mm) :");
        String endDate = scanner.nextLine().trim();
        try {
            LocalDateTime start = dateMaker(startDate);
            LocalDateTime end = dateMaker(endDate);
            System.out.println("Enter discount amount percentage :");
            int percentage = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter maximum amount of discount :");
            double maxAmount = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter usage per customer :");
            int usagePerCustomer = Integer.parseInt(scanner.nextLine());
            adminManager.createDiscountCode(code, start, end, percentage, usagePerCustomer,maxAmount);
            System.out.println("created successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("unsuccessful creation !");
        }

    }

    private LocalDateTime dateMaker(String date) throws Exception {
        String[] stringDateArray = date.split(",");
        int[] intDateArray = new int[stringDateArray.length];
        for (int i = 0; i < stringDateArray.length; i++) intDateArray[i] = Integer.parseInt(stringDateArray[i]);
        LocalDateTime localDateTime = LocalDateTime.of(intDateArray[0], intDateArray[1], intDateArray[2], intDateArray[3], intDateArray[4]);
        return localDateTime;

    }

    private void manageAllProducts() {
        while (true) {
            System.out.println("Enter productId to remove or 'back' to return :");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("back"))
                break;
            else {
                try {
                    adminManager.removeProduct(command);
                    System.out.println("removed successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void manageUsers() {
        while (true) {
            System.out.println("Enter\n1.view all users\n2.view user\n3.delete user\n4.create manager profile\n5.back");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1"))
                viewAllUsers();
            else if (choice.equals("2"))
                viewUser();
            else if (choice.equals("3"))
                deleteUser();
            else if (choice.equals("4"))
                createManagerProfile();
            else if (choice.equals("5"))
                break;
            else
                System.out.println("Invalid choice!");
        }
    }

    private void viewAllUsers() {
        ArrayList<Person>allUsers = adminManager.viewAllUsers();
        for (Person person : allUsers){
            System.out.println(person.getUsername()+" : "+person.getRole());
        }
    }

    private void createManagerProfile() {
        HashMap<String, String> data = new HashMap<>();
        System.out.println("Enter username :");
        String username = scanner.nextLine();
        if (adminManager.doesUsernameExist(username)) {
            System.out.println("username already exists!");
            return;
        }
        System.out.println("password :");
        String password = scanner.nextLine();
        data.put("username", username);
        data.put("password", password);

        System.out.println("first name :");
        data.put("name", scanner.nextLine());
        System.out.println("family name :");
        data.put("familyName", scanner.nextLine());
        System.out.println("email :");
        data.put("email", scanner.nextLine());
        System.out.println("phone number :");
        data.put("number", scanner.nextLine());
        data.put("role","admin");

        try {
            adminManager.createManager(data);
            System.out.println("Admin created!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void deleteUser() {
        System.out.println("username :");
        String username = scanner.nextLine();
        try {
            adminManager.deleteUser(username);
            System.out.println("user successfully deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewUser() {
        System.out.println("username :");
        String username = scanner.nextLine();
        try {
            Person p = adminManager.viewUser(username);
            viewPersonalInfo(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void show() {
        System.out.println("Admin Menu :");
        System.out.println("1.view personal info\n2.manage users\n3.manage all products\n4.create discount code" +
                "\n5.view discount codes\n6.manage requests\n7.manage categories\n8.Login and Register\n9.back");
    }
}
