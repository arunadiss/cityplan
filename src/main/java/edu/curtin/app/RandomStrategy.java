package edu.curtin.app; 
 
import java.util.Random; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
 
public class RandomStrategy implements BuildStrategy { 
    private static final Logger logger = Logger.getLogger(RandomStrategy.class.getName()); 
    private static final Random random = new Random(); 
 
    @Override 
    public BuildReport build(Grid grid) { 
        int totalStructures = 0; 
        double totalCost = 0.0; 
        boolean[][] successMap = new boolean[grid.getHeight()][grid.getWidth()]; 
 
        for (int row = 0; row < grid.getHeight(); row++) { 
            for (int col = 0; col < grid.getWidth(); col++) { 
                try { 
                    GridSquare square = grid.getSquare(row, col); 
 
                    int floors = random.nextInt(5) + 1; // Random floors between 1 and 5 
                    FoundationType foundation = random.nextBoolean() ? FoundationType.SLAB : FoundationType.STILTS; 
                    MaterialType material = MaterialType.values()[random.nextInt(MaterialType.values().length)]; 
                    Structure structure = createStructure(floors, foundation, material); 
 
                    // Decorate structure based on terrain 
                    if (square.getTerrain() == TerrainType.SWAMPY) { 
                        structure = new SwampyTerrainCostDecorator(structure); 
                    } else if (square.getTerrain() == TerrainType.ROCKY) { 
                        structure = new RockyTerrainCostDecorator(structure); 
                    } 
 
                    // Decorate structure based on zoning rules 
                    for (ZoningRule rule : square.getZoningRules()) { 
                        if (rule instanceof FloodRiskRule) { 
                            structure = new FloodRiskZoneDecorator(structure, ((FloodRiskRule) rule).getRisk()); 
                        } else if (rule instanceof ContaminationRule) { 
                            structure = new ContaminatedZoneDecorator(structure); 
                        } 
                    } 
 
                    BuildValidator validator = new BuildValidator(); 
                    ValidationResult result = validator.validate(square, structure); 
 
                    if (result.isValid()) { 
                        square.setStructure(structure); 
                        totalStructures++; 
                        totalCost += structure.getCost(); 
                        successMap[row][col] = true; 
                        logger.info("Built random structure at (" + row + ", " + col + ")"); 
                    } else { 
                        successMap[row][col] = false; 
                        logger.warning("Failed to build at (" + row + ", " + col + "): " + result.getReason()); 
                    } 
                } catch (Exception e) { 
                    successMap[row][col] = false; 
                    logger.log(Level.SEVERE, "Error building structure at (" + row + ", " + col + "): {0}", e.getMessage()); 
                    e.printStackTrace(); 
                } 
            } 
        } 
 
        return new BuildReport(totalStructures, totalCost, successMap); 
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