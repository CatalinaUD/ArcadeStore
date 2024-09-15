package arcadestore;

import arcadestore.models.ArcadeEnums;
import arcadestore.models.ArcadeEnums.Game_Type;
import arcadestore.models.ArcadeEnums.Game_Category;
import arcadestore.models.ArcadeEnums.Image_Quality;
import arcadestore.models.ArcadeEnums.Machine_Material;
import arcadestore.models.ArcadeEnums.Machine_Storage;
import arcadestore.models.Customer;
import arcadestore.models.Game;
import arcadestore.models.Machine;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Catalina Ariza Ardila
 */
public class ArcadeStore {
    private static final int YES = 1;
    private static List<Game> allGames;
    private static Scanner sc;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customer customer;
        List<Machine> machinesList = new ArrayList<>();
        
        allGames = new ArrayList<>();
        sc = new Scanner(System.in);        
        
        insertGames();
        printGreeting();
        System.out.println("\nPlease choose an Arcade Machine to purchase");
        
        buildMachine(machinesList);
        
        customer = createCustomer();
        customer.setMachines(machinesList);        
        
        System.out.println("\nYour purchase is ready, this is your bill: ");
        billCustomer(customer);
    }

    /**
     * Calls all the methods to build a machine
     * @param machinesList - List to add  the new machines
     * @return 
     */
    private static void buildMachine(List<Machine> machinesList) {
        Machine machine;
        int choice;
        do {
            printGames(allGames);
            machine = new Machine();
            choice = readNumberChoice(allGames.size());
            machine.setGame(allGames.get(choice - 1));

            printMaterials();
            choice = readNumberChoice(Machine_Material.values().length);
            machine.setMaterial((Machine_Material) ArcadeEnums.getEnumFromIndex(Machine_Material.values(), choice));

            printStorage();
            choice = readNumberChoice(Machine_Storage.values().length); 
            machine.setStorage((Machine_Storage) ArcadeEnums.getEnumFromIndex(Machine_Storage.values(), choice));

            printYesNoPromt("\nChoose if you want a battery for your machine:");
            choice = readNumberChoice(2);
            machine.setBattery(choice == YES);

            printImageQuality();
            choice = readNumberChoice(Image_Quality.values().length);
            machine.setImageQuality((Image_Quality) ArcadeEnums.getEnumFromIndex(Image_Quality.values(), choice));

            machine.estimatePrice();
            
            machinesList.add(machine);
            
            printYesNoPromt("\nDo you want to add another machine?");
            choice = readNumberChoice(2);
        } while (choice == YES);
    }

    /**
     * Prints the bill for the purchase of the user
     * @param customer Current customer
     */
    private static void billCustomer(Customer customer) {
        int subTotal = 0;
        double total;
        double taxes;
        int counter = 1;
        DecimalFormat formater = new DecimalFormat("##,###,###.00");
        
        printSeparator();
        System.out.println(customer.getName() + " " + customer.getLastname());
        System.out.println("Tel. " + customer.getPhone() + " - " + customer.getAdress());
        printSeparator();
        
        for (Machine machine : customer.getMachines()) {
            System.out.printf("%2s. %s\n", counter, machine);
            subTotal += machine.getPrice();
            counter++;
        }
        
        taxes = subTotal * 0.15d;
        total = subTotal + taxes;
        
        System.out.println("\n\nNumber of machines: " + customer.getMachines().size());
        System.out.printf("Subtotal : $%14s\n", formater.format(subTotal));
        System.out.printf("Taxes    : $%14s\n", formater.format(taxes));
        System.out.printf("Total    :  %14s\n", formater.format(total));
    }
    
    /**
     * Inserts all the games to be sold in to the allGames list
     */
    private static void insertGames() {
        allGames.add(new Game(Game_Category.FIGHTING, "Mortal Kombat", true, Game_Type.CLASSIC, 2));
        allGames.add(new Game(Game_Category.SHOOTING, "Doom", true, Game_Type.CLASSIC, 5));
        allGames.add(new Game(Game_Category.SHOOTING, "Quake", true, Game_Type.CLASSIC, 2));
        allGames.add(new Game(Game_Category.PLATFORM, "Mario Bros", true, Game_Type.CLASSIC, 4));
        allGames.add(new Game(Game_Category.FIGHTING, "Street Fighter Alpha 3", true, Game_Type.CLASSIC, 4));
        allGames.add(new Game(Game_Category.FIGHTING, "Tekken", true, Game_Type.CLASSIC, 3));
        allGames.add(new Game(Game_Category.PLATFORM, "Sonic the Hedgehog ", false, Game_Type.CLASSIC, 3));
        allGames.add(new Game(Game_Category.PLATFORM, "Donkey Kong Country", false, Game_Type.CLASSIC, 2));
        allGames.add(new Game(Game_Category.PLATFORM, "Mega Man X ", false, Game_Type.CLASSIC, 3));
        allGames.add(new Game(Game_Category.RACING, "Super Mario Kart", true, Game_Type.CLASSIC, 4));
        allGames.add(new Game(Game_Category.RACING, "Crash Team Racing", true, Game_Type.CLASSIC, 5));
        allGames.add(new Game(Game_Category.SHOOTING, "Call of Duty", false, Game_Type.MODERN, 7));
        allGames.add(new Game(Game_Category.SHOOTING, "Overwatch 2", false, Game_Type.MODERN, 6));
        allGames.add(new Game(Game_Category.SHOOTING, "Fornite", false, Game_Type.MODERN, 4));
        allGames.add(new Game(Game_Category.FIGHTING, "Super Smash Bros. Ultimate", true, Game_Type.MODERN, 8));
        allGames.add(new Game(Game_Category.FIGHTING, "Dragon Ball FighterZ ", true, Game_Type.MODERN, 5));
        allGames.add(new Game(Game_Category.PLATFORM, "Hollow Knight", false, Game_Type.MODERN, 6));
        allGames.add(new Game(Game_Category.PLATFORM, "Super Mario Odyssey", false, Game_Type.MODERN, 7));
        allGames.add(new Game(Game_Category.RACING, "Crash Team Racing Nitro-Fueled ", true, Game_Type.MODERN, 5));
        allGames.add(new Game(Game_Category.RACING, "F1 23", true, Game_Type.MODERN, 7));
    }

    /**
     * Reads entered text with a prompt message
     * @param message message to prompt to user
     * @return entered text
     */
    private static String readText(String message) {
        String result;
        do { 
            System.out.print(message);
            result = sc.nextLine();
        } while (result.isEmpty());
        
        return result;                 
    }
    
    /**
     * Creates a {@link Customer} by prompting a user to enter the details
     * @return a {@link Customer}
     */
    private static Customer createCustomer() {
        System.out.println("");
        String name = readText("Please, enter your name: ");
        String lastname = readText("Please, enter your lastname: ");
        String adress = readText("Please, enter your adress: ");
        String phone = readText("Please, enter your phone: ");
        
        return new Customer(name, lastname, adress, phone);
    }

    /**
     * Prints the image quality to give a user a choice
     */
    private static void printImageQuality() {
        System.out.println("\nChoose a image quality:");
        for (Image_Quality quality : Image_Quality.values()) {
            System.out.println(quality.getIndex() + ". " + quality.getValue());
        }
        printSeparator();
    }

    /**
     * Prints a message greeting the user
     */
    private static void printGreeting() {
        System.out.println("*------------------------------------------*");
        System.out.println("||              WELLCOME!!!               ||");
        System.out.println("*------------------------------------------*");
    }

    
    /**
     * Prints a Yes/No prompt for the user to choose with a message
     * 
     * @param message Message to prompt
     */
    private static void printYesNoPromt(String message) {
        System.out.println(message);
        System.out.println("1. Yes");
        System.out.println("2. No");
        printSeparator();        
    }

    /**
     * Prints the storage to give a user a choice
     */
    private static void printStorage() {
        System.out.println("\nChosse a storage size:");
        for (Machine_Storage storage : Machine_Storage.values()) {
            System.out.println(storage.getIndex() + ". " + storage.getValue());
        }
        printSeparator();
    }

    /**
     * Prints the material to give a user a choice
     */
    private static void printMaterials() {
        System.out.println("\nChoose a material:");
        for (Machine_Material material : Machine_Material.values()) {
            System.out.println(material.getIndex() +  ". " + material.getValue());
        }
        printSeparator();
    }

    /**
     * Prints a list of games to give a user a choice
     * @param allGames 
     */
    private static void printGames(List<Game> allGames) {
        System.out.println("\nChoose a game:");
        int counter = 1;
        for (Game game : allGames) {
            System.out.printf("%2s. %s\n", counter, game);
            counter++;
        }
        printSeparator();
    }
    
    /**
     * Reads a choice after prompting the user to enter a number,
     * this choice has to be smaller to the limit
     * @param limit - limit for the choice
     * @return choice
     */
    public static int readNumberChoice(int limit) {
        String message = "Enter a number: ";
        int choice;
        do {        
            try {
                System.out.print(message);
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }
            if(choice < 1 || choice > limit) {                    
                System.out.println("Invalid entry!");
            }
        } while (choice < 1 || choice > limit);
        
        return choice;
    }
    
    /**
     * Simple method to print a line to separate
     */
    public static void printSeparator() {
        System.out.println("-----------------------------------------------");
    }
}
