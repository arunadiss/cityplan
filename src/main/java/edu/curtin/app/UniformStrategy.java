package edu.curtin.app; 
 
import java.util.Scanner; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
 
public class UniformStrategy implements BuildStrategy { 
    private static final Logger logger = Logger.getLogger(UniformStrategy.class.getName()); 
 
    @Override 
    public BuildReport build(Grid grid) { 
        Scanner scanner = new Scanner(System.in); 
 
        try { 
            System.out.print("Enter number of floors for uniform structure: "); 
            int floors = Integer.parseInt(scanner.nextLine()); 
            System.out.print("Enter foundation type (SLAB or STILTS): "); 
            FoundationType foundation = FoundationType.valueOf(scanner.nextLine().toUpperCase()); 
            System.out.print("Enter material (WOOD, STONE, BRICK, CONCRETE): "); 
            MaterialType material = MaterialType.valueOf(scanner.nextLine().toUpperCase()); 
 
            Structure template = createStructure(floors, foundation, material); 
 
            int totalStructures = 0; 
            double totalCost = 0.0; 
            boolean[][] successMap = new boolean[grid.getHeight()][grid.getWidth()]; 
 
            for (int row = 0; row < grid.getHeight(); row++) { 
                for (int col = 0; col < grid.getWidth(); col++) { 
                    GridSquare square = grid.getSquare(row, col); 
 
                    // Decorate structure based on terrain 
                    if (square.getTerrain() == TerrainType.SWAMPY) { 
                        template = new SwampyTerrainCostDecorator(template); 
                    } else if (square.getTerrain() == TerrainType.ROCKY) { 
                        template = new RockyTerrainCostDecorator(template); 
                    } 
 
                    // Decorate structure based on zoning rules 
                    for (ZoningRule rule : square.getZoningRules()) { 
                        if (rule instanceof FloodRiskRule) { 
                            template = new FloodRiskZoneDecorator(template, ((FloodRiskRule) rule).getRisk()); 
                        } else if (rule instanceof ContaminationRule) { 
                            template = new ContaminatedZoneDecorator(template); 
                        } 
                    } 
 
                    BuildValidator validator = new BuildValidator(); 
                    ValidationResult result = validator.validate(square, template); 
 
                    if (result.isValid()) { 
                        square.setStructure(template); 
                        totalStructures++; 
                        totalCost += template.getCost(); 
                        successMap[row][col] = true; 
                        logger.info("Built uniform structure at (" + row + ", " + col + ")"); 
                    } else { 
                        successMap[row][col] = false; 
                        logger.warning("Failed to build at (" + row + ", " + col + "): " + result.getReason()); 
                    } 
                } 
            } 
 
            return new BuildReport(totalStructures, totalCost, successMap); 
        } catch (IllegalArgumentException e) { 
            logger.log(Level.SEVERE, "Invalid input: {0}", e.getMessage()); 
            e.printStackTrace(); 
            return new BuildReport(0, 0.0, new boolean[grid.getHeight()][grid.getWidth()]); 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage()); 
            e.printStackTrace(); 
            return new BuildReport(0, 0.0, new boolean[grid.getHeight()][grid.getWidth()]); 
        } 
    } 
 
    private Structure createStructure(int floors, FoundationType foundation, MaterialType material) { 
        try { 
            switch (material) { 
                case WOOD: 
                    return new WoodStructure(floors, foundation); 
                case STONE: 
                    return new StoneStructure(floors, foundation); 
                case BRICK: 
                    return new BrickStructure(floors, foundation); 
                case CONCRETE: 
                    return new ConcreteStructure(floors, foundation); 
                default: 
                    throw new IllegalArgumentException("Invalid material type."); 
            } 
        } catch (IllegalArgumentException e) { 
            logger.log(Level.SEVERE, "Error creating structure: {0}", e.getMessage()); 
            throw e; 
        } 
    } 
}