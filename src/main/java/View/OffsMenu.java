package View;

public class OffsMenu extends Menu{

    public OffsMenu(Menu previousMenu) {
        super("OffsMenu", previousMenu);
    }

    @Override
    public void commandProcess() {
        do {
            show();
            AllOffShow();
            System.out.println("Enter product id to show or 'back' to return.");
            String command = scanner.nextLine();
            if (command.trim().equalsIgnoreCase("back"))
                break;
            else {
                try {
                    manager.getProductById(Integer.parseInt(command));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

        }while (true);
        
    }

    private void AllOffShow() {

    }


    @Override
    public void show() {
        System.out.println("Offs Menu");

    }
}
