package View;

import model.Admin;
import model.Person;
import model.Seller;

public  class AccountMenu extends Menu {


    public AccountMenu(String name,Menu previousMenu) {
        super(name, previousMenu);
    }

    @Override
    public void commandProcess() {
        if (person == null){
            System.out.println("You need to login to access account menu.");
            LoginRegisterMenu loginRegisterMenu = new  LoginRegisterMenu(this.getPreviousMenu());
            loginRegisterMenu.run();
        }
        else if (person instanceof Admin){
            AdminMenu adminMenu = new AdminMenu(this.getPreviousMenu());
            adminMenu.run();
        }else if (person instanceof Seller){
            SellerMenu sellerMenu = new SellerMenu(this.getPreviousMenu());
        }else {
            CustomerMenu customerMenu = new CustomerMenu(this.getPreviousMenu());
        }
    }

    @Override
    public void show() {
        System.out.println("Account Menu :");

    }


    protected void viewInfo() {
        viewPersonalInfo(person);
        while (true){
            System.out.println("Enter\n1.editFields\n2.back");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1"))
                editField();
            else if (choice.equals("2"))
                break;
            else System.out.println("Invalid choice");
        }
    }
    protected void viewPersonalInfo(Person p){
        System.out.println("role : "+p.getRole());
        System.out.println("username : "+p.getUsername());
        System.out.println("password : "+p.getPassword());
        System.out.println("name : "+p.getName());
        System.out.println("family name : "+p.getFamilyName());
        System.out.println("email : "+p.getEmail());
        System.out.println("number : "+p.getNumber());
    }
    protected void editField (){
        System.out.println("Enter field to change:\n1.password\n2.name\n3.family name\n4.email\n5.number");
        String choice = scanner.nextLine().trim();
        String field;
        if (choice.equals("1"))
            field = "password";
        else if (choice.equals("2"))
            field = "name";
        else if (choice.equals("3"))
            field = "familyName";
        else if (choice.equals("4"))
            field = "email";
        else if (choice.equals("5"))
            field = "number";
        else{
            System.out.println("Invalid choice");
            return;
        }
        System.out.println("Enter new value :");
        String newValue = scanner.nextLine().trim();
        try {
            manager.editField(field,newValue);
            System.out.println("Field successfully changed!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
