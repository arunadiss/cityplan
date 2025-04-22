package edu.curtin.app; 
 
import java.util.Scanner; 
import java.util.logging.Logger; 
 
public class MenuController { 
    private static final Logger logger = Logger.getLogger(MenuController.class.getName()); 
    private App app; 
    private Scanner scanner; 
 
    public MenuController(App app) { 
        this.app = app; 
        this.scanner = new Scanner(System.in); 
    } 
 
    public void start() { 
        logger.info("Starting menu system..."); 
        boolean running = true; 
        while (running) { 
            displayMenu(); 
            int choice = getChoice(); 
            running = handleChoice(choice); 
        } 
    } 
 
    private void displayMenu() { 
        System.out.println("\nCity Planning Menu:"); 
        System.out.println("1. Build Structure"); 
        System.out.println("2. Build City"); 
        System.out.println("3. Configure Build Strategy"); 
        System.out.println("4. Quit"); 
        System.out.print("Enter your choice: "); 
    } 
 
    private int getChoice() { 
        int choice = -1; 
        try { 
            choice = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException e) { 
            logger.warning("Invalid input. Please enter a number between 1 and 4."); 
        } 
        return choice; 
    } 
 
    private boolean handleChoice(int choice) { 
        switch (choice) { 
            case 1: 
                app.buildStructure(); 
                break; 
            case 2: 
                app.buildCity(); 
                break; 
            case 3: 
                configureStrategy(); 
                break; 
            case 4: 
                System.out.println("Exiting application..."); 
                return false; 
            default: 
                System.out.println("Invalid choice. Please enter a number between 1 and 4."); 
                break; 
        } 
        return true; 
    } 
 
    private void configureStrategy() { 
        System.out.println("Choose build strategy (uniform, random, central): "); 
        String strategy = scanner.nextLine().trim().toLowerCase(); 
        app.configureStrategy(strategy); 
    } 
}