import java.io.*;
import java.util.*;
public class GroceryManagementSystem {
static final String INVENTORY_FILE_NAME = "inventory.txt";
static Map<String, Integer> inventory;
 public static void main(String[] args) {
 inventory = new HashMap<>();
 readInventoryFromFile();
 Scanner sc = new Scanner(System.in);
 int option = 0;
 do {
 System.out.println("Grocery Management System");
 System.out.println("-------------------------");
 System.out.println("1. Add item to inventory");
 System.out.println("2. Remove item from inventory");
 System.out.println("3. Display inventory");
 System.out.println("4. Exit");
 System.out.print("Enter option: ");
 option = sc.nextInt();
 switch (option) {
 case 1:
 addItemToInventory(sc);
 break;
 case 2:
 removeItemFromInventory(sc);
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
  static void addItemToInventory(Scanner sc) {
 System.out.print("Enter item name: ");
 String itemName = sc.next();
 System.out.print("Enter item quantity: ");
 int quantity = sc.nextInt();
 if (inventory.containsKey(itemName)) {
 int currentQuantity = inventory.get(itemName);
 inventory.put(itemName, currentQuantity + quantity);
 } else {
 inventory.put(itemName, quantity);
 }
 System.out.println("Item added to inventory");
 }
  static void removeItemFromInventory(Scanner sc) {
 System.out.print("Enter item name: ");
 String itemName = sc.next();
 if (inventory.containsKey(itemName)) {
 int currentQuantity = inventory.get(itemName);
 System.out.print("Enter item quantity to remove: ");
 int quantity = sc.nextInt();
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
  static void displayInventory() {
 System.out.println("Inventory:");
 for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
 System.out.println(entry.getKey() + ": " + entry.getValue());
 }
 }
  static void readInventoryFromFile() {
 try (Scanner sc = new Scanner(new File(INVENTORY_FILE_NAME))) {
 while (sc.hasNextLine()) {
 String line = sc.nextLine();
 sc.nextLine();
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
  static void saveInventoryToFile() {
 try (PrintWriter writer = new PrintWriter(new 
File(INVENTORY_FILE_NAME))) {
 for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
 writer.println(entry.getKey() + ", " + entry.getValue());
 }
 } catch (FileNotFoundException e) {
 System.out.println("Error saving inventory to file: " + e.getMessage());
 }}}

