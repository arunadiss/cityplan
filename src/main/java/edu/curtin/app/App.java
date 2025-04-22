package edu.curtin.app; 
 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.HashSet; 
import java.util.Scanner; 
import java.util.Set; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
 
/** 
 * Entry point into the application. To change the package, and/or the name of this class, make 
 * sure to update the 'mainClass = ...' line in build.gradle. 
 */ 
public class App 
{ 
    private static final Logger logger = Logger.getLogger(App.class.getName()); 
    private Grid grid; 
    private BuildStrategy strategy = new RandomStrategy(); // Default to RandomStrategy 
 
    public static void main(String[] args) 
    { 
        if (args.length != 1) { 
            logger.severe("Usage: java App <path_to_grid_data_file>"); 
            return; 
        } 
 
        App app = new App(); 
        app.loadGrid(args[0]); 
        app.startMenu(); // Start the menu system 
    } 
 
    public void loadGrid(String filename) { 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { 
            String[] dimensions = reader.readLine().split(","); 
            int height = Integer.parseInt(dimensions[0].trim()); 
            int width = Integer.parseInt(dimensions[1].trim()); 
            grid = new Grid(width, height); 
            logger.info("Grid dimensions set to: " + width + "x" + height); 
 
            for (int row = 0; row < height; row++) { 
                String[] lineData = reader.readLine().split(","); 
                for (int col = 0; col < width; col++) { 
                    String[] squareData = lineData[col].split(" "); 
                    TerrainType terrain = TerrainType.valueOf(squareData[0].toUpperCase()); 
                    Set<ZoningRule> zoningRules = new HashSet<>(); 
                    for (int i = 1; i < squareData.length; i++) { 
                        String[] ruleData = squareData[i].split("="); 
                        switch (ruleData[0]) { 
                            case "heritage": 
                                zoningRules.add(new HeritageRule(MaterialType.valueOf(ruleData[1].toUpperCase()))); 
                                break; 
                            case "height-limit": 
                                zoningRules.add(new HeightLimitRule(Integer.parseInt(ruleData[1]))); 
                                break; 
                            case "flood-risk": 
                                zoningRules.add(new FloodRiskRule(Double.parseDouble(ruleData[1]))); 
                                break; 
                            case "contamination": 
                                zoningRules.add(new ContaminationRule(true)); 
                                break; 
                            default: 
                                logger.warning("Unknown zoning rule: " + ruleData[0]); 
                        } 
                    } 
                    grid.setSquare(row, col, new GridSquare(terrain, zoningRules)); 
                } 
            } 
            logger.info("Grid loaded successfully from file: " + filename); 
        } catch (IOException e) { 
            logger.log(Level.SEVERE, "I/O error while loading grid: {0}", e.getMessage()); 
            e.printStackTrace(); 
        } catch (IllegalArgumentException e) { 
            logger.log(Level.SEVERE, "Invalid argument: {0}", e.getMessage()); 
            e.printStackTrace(); 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
 
    public void startMenu() { 
        MenuController menuController = new MenuController(this); 
        menuController.start(); 
    } 
 
    public void buildStructure() { 
        Scanner scanner = new Scanner(System.in); 
        try { 
            System.out.print("Enter row: "); 
            int row = Integer.parseInt(scanner.nextLine()); 
            System.out.print("Enter column: "); 
            int col = Integer.parseInt(scanner.nextLine()); 
            System.out.print("Enter number of floors: "); 
            int floors = Integer.parseInt(scanner.nextLine()); 
            System.out.print("Enter foundation type (SLAB or STILTS): "); 
            FoundationType foundation = FoundationType.valueOf(scanner.nextLine().toUpperCase()); 
            System.out.print("Enter material (WOOD, STONE, BRICK, CONCRETE): "); 
            MaterialType material = MaterialType.valueOf(scanner.nextLine().toUpperCase()); 
 
            Structure structure; 
            switch (material) { 
                case WOOD: 
                    structure = new WoodStructure(floors, foundation); 
                    break; 
                case STONE: 
                    structure = new StoneStructure(floors, foundation); 
                    break; 
                case BRICK: 
                    structure = new BrickStructure(floors, foundation); 
                    break; 
                case CONCRETE: 
                    structure = new ConcreteStructure(floors, foundation); 
                    break; 
                default: 
                    logger.severe("Invalid material type."); 
                    return; 
            } 
 
            GridSquare gridSquare = grid.getSquare(row, col); 
            if (gridSquare == null) { 
                logger.warning("Invalid grid position: (" + row + ", " + col + ")"); 
                System.out.println("Invalid grid position."); 
                return; 
            } 
 
            // Decorate structure based on terrain 
            if (gridSquare.getTerrain() == TerrainType.SWAMPY) { 
                structure = new SwampyTerrainCostDecorator(structure); 
            } else if (gridSquare.getTerrain() == TerrainType.ROCKY) { 
                structure = new RockyTerrainCostDecorator(structure); 
            } 
 
            // Decorate structure based on zoning rules 
            for (ZoningRule rule : gridSquare.getZoningRules()) { 
                if (rule instanceof FloodRiskRule) { 
                    structure = new FloodRiskZoneDecorator(structure, ((FloodRiskRule) rule).getRisk()); 
                } else if (rule instanceof ContaminationRule) { 
                    structure = new ContaminatedZoneDecorator(structure); 
                } 
            } 
 
            BuildValidator validator = new BuildValidator(); 
            ValidationResult result = validator.validate(gridSquare, structure); 
 
            if (result.isValid()) { 
                gridSquare.setStructure(structure); 
                System.out.println("Structure built successfully. Cost: " + structure.getCost()); 
            } else { 
                System.out.println("Cannot build structure: " + result.getReason()); 
            } 
        } catch (IllegalArgumentException e) { 
            logger.log(Level.SEVERE, "Invalid input: {0}", e.getMessage()); 
            System.out.println("Invalid input. Please try again."); 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
 
    public void buildCity() { 
        logger.info("Building city using strategy: " + strategy.getClass().getSimpleName()); 
        BuildReport report = strategy.build(grid); 
        System.out.println("Total structures built: " + report.getTotalStructures()); 
        System.out.println("Total cost: " + report.getTotalCost()); 
        displaySuccessMap(report.getMap()); 
    } 
 
    private void displaySuccessMap(boolean[][] map) { 
        System.out.println("Success Map:"); 
        for (boolean[] row : map) { 
            for (boolean success : row) { 
                System.out.print(success ? "X " : ". "); 
            } 
            System.out.println(); 
        } 
    } 
 
    public void configureStrategy(String type) { 
        switch (type) { 
            case "uniform": 
                strategy = new UniformStrategy(); 
                break; 
            case "random": 
                strategy = new RandomStrategy(); 
                break; 
            case "central": 
                strategy = new CentralStrategy(); 
                break; 
            default: 
                System.out.println("Invalid strategy type. Defaulting to random."); 
                strategy = new RandomStrategy(); 
                break; 
        } 
        logger.info("Configured build strategy to: " + strategy.getClass().getSimpleName()); 
    } 
}