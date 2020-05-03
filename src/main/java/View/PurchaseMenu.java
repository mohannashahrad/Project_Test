package View;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu previousMenu) {
        super("PurchaseMenu", previousMenu);
    }

    @Override
    public void commandProcess() {

    }

    @Override
    public void show() {
        System.out.print("Purchase Menu -> ");
    }
}
