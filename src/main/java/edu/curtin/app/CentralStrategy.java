package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class CentralStrategy implements BuildStrategy { 
    private static final Logger logger = Logger.getLogger(CentralStrategy.class.getName()); 
 
    @Override 
    public BuildReport build(Grid grid) { 
        int totalStructures = 0; 
        double totalCost = 0.0; 
        boolean[][] successMap = new boolean[grid.getHeight()][grid.getWidth()]; 
 
        int centerX = grid.getWidth() / 2; 
        int centerY = grid.getHeight() / 2; 
 
        for (int row = 0; row < grid.getHeight(); row++) { 
            for (int col = 0; col < grid.getWidth(); col++) { 
                GridSquare square = grid.getSquare(row, col); 
                int distance = Math.abs(centerX - col) + Math.abs(centerY - row); 
                int floors = Math.max(1, (int) Math.round(1 + 20.0 / (distance + 1))); 
                Structure structure = new ConcreteStructure(floors, FoundationType.SLAB); 
 
                BuildValidator validator = new BuildValidator(); 
                ValidationResult result = validator.validate(square, structure); 
 
                if (result.isValid()) { 
                    square.setStructure(structure); 
                    totalStructures++; 
                    totalCost += structure.getCost(); // Apply additional cost modifiers here 
                    successMap[row][col] = true; 
                    logger.info("Built structure at (" + row + ", " + col + ")"); 
                } else { 
                    successMap[row][col] = false; 
                    logger.warning("Failed to build at (" + row + ", " + col + "): " + result.getReason()); 
                } 
            } 
        } 
 
        return new BuildReport(totalStructures, totalCost, successMap); 
    } 
} 