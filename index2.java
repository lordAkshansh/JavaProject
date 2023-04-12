import java.util.Scanner;
// Added code to this repo 
public class GroceryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static int inventoryCount = 0;
    private static int MAX_INVENTORY = 100;
    private static GroceryItem[] inventory = new GroceryItem[MAX_INVENTORY];
    
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nGrocery Management System\n");
            System.out.println("1. Add item to inventory");
            System.out.println("2. Display inventory");
            System.out.println("3. Search for an item");
            System.out.println("4. Remove an item");
            System.out.println("5. Exit");
            
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            
            switch(choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    displayInventory();
                    break;
                case 3:
                    searchItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    System.out.println("\nExiting the program...");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while(choice != 5);
    }
    
    private static void addItem() {
        if(inventoryCount < MAX_INVENTORY) {
            scanner.nextLine();
            System.out.print("\nEnter item name: ");
            String itemName = scanner.nextLine();
            
            System.out.print("\nEnter item price: ");
            double price = scanner.nextDouble();
            
            System.out.print("\nEnter item quantity: ");
            int quantity = scanner.nextInt();
            
            inventory[inventoryCount++] = new GroceryItem(itemName, price, quantity);
            System.out.println("\nItem added successfully!");
        } else {
            System.out.println("\nInventory is full. Cannot add more items.");
        }
    }
    
    private static void displayInventory() {
        if(inventoryCount == 0) {
            System.out.println("\nInventory is empty.");
        } else {
            System.out.println("\nInventory:");
            System.out.printf("%-20s%-10s%-10s\n", "Item Name", "Price", "Quantity");
            for(int i=0; i<inventoryCount; i++) {
                System.out.printf("%-20s%-10.2f%-10d\n", inventory[i].getName(), inventory[i].getPrice(), inventory[i].getQuantity());
            }
        }
    }
    
    private static void searchItem() {
        scanner.nextLine();
        System.out.print("\nEnter item name to search: ");
        String itemName = scanner.nextLine();
        
        boolean found = false;
        for(int i=0; i<inventoryCount; i++) {
            if(inventory[i].getName().equalsIgnoreCase(itemName)) {
                System.out.printf("%-20s%-10s%-10s\n", "Item Name", "Price", "Quantity");
                System.out.printf("%-20s%-10.2f%-10d\n", inventory[i].getName(), inventory[i].getPrice(), inventory[i].getQuantity());
                found = true;
                break;
            }
        }
        
        if(!found) {
            System.out.println("\nItem not found in inventory.");
        }
    }
    
    private static void removeItem() {
        scanner.nextLine();
        System.out.print("\nEnter item name to remove: ");
        String itemName = scanner.nextLine();
        
        boolean found = false;
        for(int i=0; i<inventoryCount; i++) {
            if(inventory[i].getName().equalsIgnoreCase(itemName)) {
                for(int j=i; j<inventoryCount-1; j++) {
                    inventory[j] = inventory[j+1];
                }
                inventoryCount--;
                System.out.println("\nItem removed successfully!");
                found = true;
                break;
            }
        }
        
        if(!found) {
            System.out.println("\nItem not found in inventory.");
        }
    }
}

class GroceryItem {
    private String name;
    private double price;
    private int quantity;
    
    public GroceryItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
}
