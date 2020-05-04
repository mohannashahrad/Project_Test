package View;

public class OffsMenu extends Menu{

    public OffsMenu(Menu previousMenu) {
        super("OffsMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        while (true){
            AllOffShow();
        }
        
    }

    private void AllOffShow() {
    }

    @Override
    public void show() {
        System.out.println("Offs Menu");
        System.out.println("Enter product id to show or 'back' to return.");

    }
}
