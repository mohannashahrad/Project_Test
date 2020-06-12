package graphics;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Person;

import java.util.ArrayList;

public class Menu {
   // private static ArrayList<Menu> allMenus = new ArrayList<>();
    public static Stage MainStage = View.getStage();
    protected static Person person;
    private Parent root;
    private Scene scene;
   /* private String name;

    public static boolean doesMenuExist(String name){
        for (Menu menu : allMenus){
            if (menu.getName().equals(name))
                return true;
        }
        return false;
    }

    public static Menu getMenuByName(String name){
        for (Menu menu : allMenus){
            if (menu.getName().equals(name))
                return menu;
        }
        return null;
    }

    private Menu(String name){
        this.setName(name);
        allMenus.add(this);
    }
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */


    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Menu.person = person;
    }



}
