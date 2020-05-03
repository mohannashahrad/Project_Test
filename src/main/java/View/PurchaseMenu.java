package View;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu previousMenu) {
        super("PurchaseMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        new Menu("RecieveInfoMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {

            }

            @Override
            public void show() {
                System.out.println("Recieve Information Menu :");

            }
        }.run();
        new Menu("DiscountCodeMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {

            }

            @Override
            public void show() {

            }
        }.run();
        new Menu("PaymentMenu",PurchaseMenu.this){

            @Override
            public void commandProcess() {

            }

            @Override
            public void show() {

            }
        }.run();
        this.getPreviousMenu().run();
    }

    @Override
    public void show() {
        System.out.print("Purchase Menu :");
    }
}
