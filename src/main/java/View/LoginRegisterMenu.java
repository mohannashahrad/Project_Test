package View;

import java.util.HashMap;

public class LoginRegisterMenu extends Menu {

    public LoginRegisterMenu(Menu previousMenu) {
        super("LoginRegisterMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        while(true){
            String command = scanner.nextLine().trim();
            if(command.equals("1")){
                if (login())
                    break;
            }
            else if(command.equals("2")){
                register();
            }
            else if(command.equals("3"))
                break;
            else if(command.equalsIgnoreCase("help"))
                this.show();
            else
                System.out.println("Invalid choice");
        }
        this.getPreviousMenu().run();
    }


    private boolean register() {

        System.out.println("Enter type :");
        String type = scanner.nextLine().trim();
        if(!(type.equalsIgnoreCase("admin") || type.equalsIgnoreCase("seller") || type.equalsIgnoreCase("customer"))){
            System.out.println("Invalid type");
            return false;
        }
        HashMap<String,String> data = new HashMap<>();
        data = personGetInfo(type);
        if (data == null)
            return false;

        try {
            manager.register(data);
            System.out.println("register successful!");
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    private HashMap<String,String> personGetInfo(String type){
        HashMap<String,String> data = new HashMap<>();
        System.out.println("Enter username :");
        String username = scanner.nextLine();
        if(manager.doesUsernameExist(username)){
            System.out.println("username already exists!");
            return null;
        }
        System.out.println("password :");
        String password = scanner.nextLine();
        data.put("type",type);
        data.put("username",username);
        data.put("password",password);

        System.out.println("first name :");
        data.put("name",scanner.nextLine());
        System.out.println("family name :");
        data.put("familyName",scanner.nextLine());
        System.out.println("email :");
        data.put("email",scanner.nextLine());
        System.out.println("phone number :");
        data.put("number",scanner.nextLine());
        if (type.equalsIgnoreCase("seller")){
            System.out.println("company name :");
            data.put("company",scanner.nextLine());
        }
        return data;
    }

    private boolean login() {
        System.out.println("Enter username :");
        String username=scanner.nextLine();
        if (! manager.doesUsernameExist(username)){
            System.out.println("username does not exist");
            return false;
        }
        System.out.println("password :");
        String password=scanner.nextLine();
        try {
            Menu.setPerson(manager.login(username,password));
            System.out.println("login successful!");
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void show() {
        System.out.println("LoginRegisterMenu :");
        System.out.println("Enter your chioce :\n1.Login\n2.Register\n3.Back");
    }
}
