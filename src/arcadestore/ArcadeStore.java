package arcadestore;

import Data.DatabaseReader;
import arcadestore.models.ArcadeEnums;
import arcadestore.models.ArcadeEnums.ArrowCardinalities_Type;
import arcadestore.models.ArcadeEnums.Controller_Type;
import arcadestore.models.ArcadeEnums.Game_Category;
import arcadestore.models.ArcadeEnums.Glasses_Type;
import arcadestore.models.ArcadeEnums.Image_Quality;
import arcadestore.models.ArcadeEnums.Machine_Color;
import arcadestore.models.Machine_Material;
import arcadestore.models.ArcadeEnums.WeaponType;
import arcadestore.models.ClassicalArcadeMachine;
import arcadestore.models.Customer;
import arcadestore.models.DanceRevolution;
import arcadestore.models.Game;
import arcadestore.models.IEnumeration;
import arcadestore.models.Machine;
import arcadestore.models.RacingMachine;
import arcadestore.models.ShootingMachine;
import arcadestore.models.VirtualRealityMachine;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Catalina Ariza Ardila
 */
public class ArcadeStore {
    private static final int YES = 1;
    private static List<Game> allGames;
    private static List<Machine> presetupMachines;
    private static Scanner sc;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customer customer;
        List<Machine> clientMachines = new ArrayList<>();
        
        sc = new Scanner(System.in);        
        
        createPresetupMachines();
        insertGames();
        
        //------
        printGreeting();
        System.out.println("\nPlease choose an Arcade Machine to purchase");
        
        printMenu();
        
        buildMachine(clientMachines);
        
        customer = createCustomer();
        customer.setMachines(clientMachines);        
        
        System.out.println("\nYour purchase is ready, this is your bill: ");
        billCustomer(customer);
    }
    
    /**
     * Adds all presetup machines to the list of presetupMachines
     */
    private static void createPresetupMachines() {
        presetupMachines = new ArrayList<>();
        presetupMachines.add(createClassicalArcadeMachine());
        presetupMachines.add(createDanceRevolutionMachine());
        presetupMachines.add(createShootingMachine());
        presetupMachines.add(createRacingMachine());
        presetupMachines.add(createVirtualRealityMachine());
    }
    
    /**
     * Creates a {@link VirtualRealityMachine}
     * @return a {@link VirtualRealityMachine}
     */
    private static VirtualRealityMachine createVirtualRealityMachine() {
        int[] dim = {175, 95, 70}; //height, width, depth
        Machine.MachineBuilder builder = new Machine.MachineBuilder(4000000, 50,
                32, ArcadeEnums.Processor_Type.INTEL)
                .imageQuality(Image_Quality.SD)
                .powerConsumption(60)
                .dimensions(dim);
        VirtualRealityMachine vr = new VirtualRealityMachine(builder);
        vr.setGlassesType(Glasses_Type.VALVE_INDEX);
        return vr;
    }
        
     /**
     * Creates a {@link RacingMachine}
     * @return a {@link RacingMachine}
     */
    private static RacingMachine createRacingMachine() {
        int[] dim = {170, 85, 80}; //height, width, depth
        Machine.MachineBuilder builder = new Machine.MachineBuilder(2500000, 30,
                10, ArcadeEnums.Processor_Type.INTEL)
                .imageQuality(Image_Quality.SD)
                .powerConsumption(300)
                .dimensions(dim);
        RacingMachine rm = new RacingMachine(builder);
        rm.setController(Controller_Type.WHEEL);
        rm.setNumberOfControllers(1);      
        
        return rm;
    }   
    
    /**
     * Creates a {@link ShootingMachine}
     * @return a {@link ShootingMachine}
     */
    private static ShootingMachine createShootingMachine() {
        int[] dim = {175, 90, 80}; //height, width, depth
        Machine.MachineBuilder builder = new Machine.MachineBuilder(2700000, 40,
                20, ArcadeEnums.Processor_Type.RYZEN)
                .imageQuality(Image_Quality.HD)
                .powerConsumption(350)
                .dimensions(dim);
        
        ShootingMachine sm = new ShootingMachine(builder);
        sm.setNumberOfWeapons(2);
        sm.setBluetooth(true);
        sm.setWeaponType(WeaponType.LIGHT_GUN);
                
        return sm;        
    }   
            
    /**
     * Creates a {@link DanceRevolution}
     * @return a {@link DanceRevolution}
     */
    private static DanceRevolution createDanceRevolutionMachine() {
        int[] dim = {180, 100, 200}; //height, width, depth
        Machine.MachineBuilder builder = new Machine.MachineBuilder(3000000, 60,
                16, ArcadeEnums.Processor_Type.RYZEN)
                .imageQuality(Image_Quality.SD)
                .powerConsumption(200)
                .dimensions(dim);
        
        DanceRevolution dr = new DanceRevolution(builder);
        dr.setArrowCardinalities(ArrowCardinalities_Type.FULL);
        
        return dr;
    }
    
    /**
     * Creates a {@link ClassicalArcadeMachine}
     * @return a {@link ClassicalArcadeMachine}
     */
    private static ClassicalArcadeMachine createClassicalArcadeMachine() {
        int[] dim = {170, 65, 80}; //height, width, depth
        Machine.MachineBuilder builder = new Machine.MachineBuilder(2000000, 25,
                7, ArcadeEnums.Processor_Type.INTEL)
                .imageQuality(Image_Quality.SD)
                .powerConsumption(140)
                .dimensions(dim);
        
        ClassicalArcadeMachine cam = new ClassicalArcadeMachine(builder);
        return cam;
    }
    
    /**
     * Prompts the user to select one of the presetup machines
     * @return selected machine
     */
    private static void printPrebuiltMachine() {
        System.out.println("Select one of the pre-built machines: ");
        System.out.println("1. Classical Arcade Machine");
        System.out.println("2. Dance Revolution");
        System.out.println("3. Shooting Machine");
        System.out.println("4. Racing Machine");
        System.out.println("5. Virtual Reality Machine");
    }
    

    /**
     * Calls all the methods to build a machine
     * @param machinesList - List to add the new machines
     * @return 
     */
    private static void buildMachine(List<Machine> machinesList) {
        Machine machine;
        int choice;
        do {
            List<Game> machineGames;
            printPrebuiltMachine();
            choice = readNumberChoice(presetupMachines.size());
            machine = presetupMachines.get(choice - 1);
            
            printMaterials();
            choice = readNumberChoice(Machine_Material.values().length);
            machine.setMaterial((Machine_Material) ArcadeEnums.getEnumFromIndex(Machine_Material.values(), choice));
            
            printColors();
            choice = readNumberChoice(Machine_Color.values().length);
            machine.setColor((Machine_Color) ArcadeEnums.getEnumFromIndex(Machine_Color.values(), choice));
            
            printYesNoPromt("\nChoose if you want a battery for your machine:");
            choice = readNumberChoice(2);
            machine.setBattery(choice == YES);
                   
            machineGames = selectGamesForMachine(findAllowedCategory(machine));
            machine.setGameList(machineGames);     
            machine.estimatePrice();          
            machinesList.add(machine);   
            
            printYesNoPromt("\nDo you want to add another machine?");
            choice = readNumberChoice(2);
        } while (choice == YES);
    }
    
    
    public static List<Game> selectGamesForMachine(Game_Category category) {
        List<Game> selectedGames = new ArrayList<>();
        List<Game> filteredGames = filterGamesList(allGames, category);
        int maxSelection = filteredGames.size() < 5 ? filteredGames.size() : 5;
        System.out.printf("\nEnter the number of games to add to the machine (Max %s)\n",
                maxSelection);
        int count = readNumberChoice(maxSelection);
        do {            
            printGames(filteredGames, false);
            int choice = readNumberChoice(maxSelection);
            selectedGames.add(filteredGames.get(choice - 1));
            count--;
        } while (count > 0);
        return selectedGames;
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
            DecimalFormat gameFormater = new DecimalFormat("###,###.00");
            System.out.printf("%2s. %s\n", counter, machine);
            System.out.println("    Games:");
            for (Game game : machine.getGameList()) {
                
                System.out.printf("    - %-32s $... %10s\n", 
                        game.getName(), gameFormater.format(game.getPrice()));
            }
            subTotal += machine.getPrice();
            counter++;
        }
        
        taxes = subTotal * 0.15d;
        total = subTotal + taxes;
        
        System.out.println("\n\nNumber of machines: " + customer.getMachines().size());
        System.out.printf("Subtotal : $%14s\n", formater.format(subTotal));
        System.out.printf("Taxes    : $%14s\n", formater.format(taxes));
        System.out.printf("Total    : $%14s\n", formater.format(total));
    }
    
    /**
     * Inserts all the games to be sold in to the allGames list
     */
    private static void insertGames() {
        DatabaseReader db = new DatabaseReader();
        allGames = db.getGames();        
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
     * Prints the material to give a user a choice
     */
    private static void printMaterials() {
        System.out.println("\nChoose a material:");
        printEnum(Machine_Material.values());
        printSeparator();
    }
    
    private static void printColors()  {
        System.out.println("\nChoose a colors:");
        printEnum(Machine_Color.values());
        printSeparator();
    }
    
    /**
     * Prints the values of a enum
     */
    private static void printEnum(IEnumeration[] values) {
        for (IEnumeration e: values) {
            System.out.println(e.getIndex() +  ". " + e.getValue());
        }
    }
    
    /**
     * 
     * @param machine
     * @return 
     */
    private static Game_Category findAllowedCategory(Machine machine) {
        if (machine instanceof RacingMachine) {
            return Game_Category.RACING;
        }
        
        if (machine instanceof VirtualRealityMachine) {
            return Game_Category.VIRTUAL_REALITY;
        }
        
        if (machine instanceof ShootingMachine) {
            return Game_Category.SHOOTING;
        }
        
        if (machine instanceof DanceRevolution) {
            return Game_Category.DANCE;
        }
        
        return null;
    }

    /**
     * 
     * @param games
     * @param isSimpleDisplay
     * @return 
     */
    private static List<Game> printGames(List<Game> games, boolean isSimpleDisplay) {
        System.out.println("\nChoose a game:");
        int counter = 1;
        for (Game game : games) {
            if(isSimpleDisplay) {
                System.out.printf("%2s. %s\n", counter, game.showBasicInformation());
            } else {
                System.out.printf("%2s. %s\n", counter, game);
            }
            counter++;
        }
        printSeparator();
        
        return games;
    }

    /**
     * 
     * @param allGames
     * @param filter
     * @return 
     */
    private static List<Game> filterGamesList(List<Game> allGames, Game_Category filter) {
        List<Game> games;
        if (filter != null) {
            games = allGames.stream().filter(g -> g.getCategory() == filter).collect(Collectors.toList());
        } else {
            games = allGames;
        }
        return games;
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

    private static void printMenu() {
        int choice;
        do {
            System.out.println("\nMenu");
            System.out.println("1. See all games");
            System.out.println("2. See all colors");
            System.out.println("3. See all meterials");            
            System.out.println("4. Build a machine");
            
            choice = readNumberChoice(4);
            switch(choice) {
                case  1:
                    printGames(allGames, true);
                    break;
                case 2:
                    printEnum(Machine_Color.values());
                    break;
                case 3:
                    printEnum(Machine_Material.values());
                    break;
            }
            
            printSeparator();           
        } while (choice != 4);
    }
    
    
}
