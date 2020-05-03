package View;

import controller.Manager;
import model.Person;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    public static Scanner scanner;
    protected static Manager manager;


    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Menu.person = person;
    }

    protected static Person person;
    protected static HashMap<String,Menu> allRootMenus;

    private String name;
    private Menu previousMenu;

    static {
        scanner = new Scanner(System.in);
        allRootMenus = new HashMap<String, Menu>();
    }
    protected static Menu getMenuByName(String name){
        if (!allRootMenus.containsKey(name)){
            return null;
        }
        return allRootMenus.get(name);
    }

    public Menu(String name, Menu previousMenu) {
        this.name = name;
        this.previousMenu = previousMenu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    public Menu getPreviousMenu() {
        return previousMenu;
    }

    public abstract void commandProcess ();
    public abstract void show ();
    public  void run (){
        this.show();
        this.commandProcess();
    }
}
