import java.util.ArrayList;
import java.util.Scanner;
// initial commit
public class GroceryManagementSystem {
    private ArrayList<String> menu;

    public GroceryManagementSystem() {
        menu = new ArrayList<String>();
    }

    public void addMenuItem(String item) {
        menu.add(item);
    }

    public void removeMenuItem(String item) {
        menu.remove(item);
    }

    public void displayMenu() {
        System.out.println("Grocery Menu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i+1) + ". " + menu.get(i));
        }
    }

    public static void main(String[] args) {
        GroceryManagementSystem system = new GroceryManagementSystem();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add item to menu");
            System.out.println("2. Remove item from menu");
            System.out.println("3. Display menu");
            System.out.println("4. Exit");

            int option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter item name:");
                    String item = input.next();
                    system.addMenuItem(item);
                    System.out.println("Item added to menu!");
                    break;

                case 2:
                    System.out.println("Enter item name:");
                    item = input.next();
                    system.removeMenuItem(item);
                    System.out.println("Item removed from menu!");
                    break;

                case 3:
                    system.displayMenu();
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
