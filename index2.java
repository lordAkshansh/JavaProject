import java.io.*;
import java.util.*;

public class GroceryManagementSystem {

    private static final String INVENTORY_FILE_NAME = "inventory.txt";
    private static Map<String, Integer> inventory;

    public static void main(String[] args) {
        inventory = new HashMap<>();
        readInventoryFromFile();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Grocery Management System");
            System.out.println("-------------------------");
            System.out.println("1. Add item to inventory");
            System.out.println("2. Remove item from inventory");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit");

            System.out.print("Enter option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addItemToInventory(scanner);
                    break;
                case 2:
                    removeItemFromInventory(scanner);
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option selected");
                    break;
            }
        } while (option != 4);

        saveInventoryToFile();
    }

    private static void addItemToInventory(Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            inventory.put(itemName, quantity);
        }
        System.out.println("Item added to inventory");
    }

    private static void removeItemFromInventory(Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            System.out.print("Enter item quantity to remove: ");
            int quantity = scanner.nextInt();
            if (currentQuantity >= quantity) {
                inventory.put(itemName, currentQuantity - quantity);
                System.out.println("Item removed from inventory");
            } else {
                System.out.println("Error: Insufficient stock for item " + itemName);
            }
        } else {
            System.out.println("Error: Item " + itemName + " not found in inventory");
        }
    }

    private static void displayInventory() {
        System.out.println("Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void readInventoryFromFile() {
        try (Scanner scanner = new Scanner(new File(INVENTORY_FILE_NAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    inventory.put(itemName, quantity);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found, creating new inventory...");
        }
    }

    private static void saveInventoryToFile() {
        try (PrintWriter writer = new PrintWriter(new File(INVENTORY_FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.println(entry.getKey() + ", " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving inventory to file: " + e.getMessage());
        }}}
