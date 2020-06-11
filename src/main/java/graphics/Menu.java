package graphics;
import javafx.stage.Stage;
import model.Person;

import java.util.ArrayList;

public class Menu {
    private static ArrayList<Menu> allMenus = new ArrayList<>();
    public static Stage MainStage;
    protected static Person person;
    private String name;
    public boolean doesMenuExist(String name){
        for (Menu menu : allMenus){
            if (menu.getName().equals(name))
                return true;
        }
        return false;
    }
    private Menu(String name){
        this.setName(name);
        allMenus.add(this);
    }
    public static Menu getMenuByName(String name){
        for (Menu menu : allMenus){
            if (menu.getName().equals(name))
                return menu;
        }
        return null;
    }

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Menu.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
