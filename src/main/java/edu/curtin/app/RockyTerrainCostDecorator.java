package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class RockyTerrainCostDecorator extends StructureDecorator { 
    private static final Logger logger = Logger.getLogger(RockyTerrainCostDecorator.class.getName()); 
 
    public RockyTerrainCostDecorator(Structure structure) { 
        super(structure); 
        logger.info("RockyTerrainCostDecorator created for structure: " + structure); 
    } 
 
    @Override 
    public double getCost() { 
        double originalCost = structure.getCost(); 
        double adjustedCost = originalCost * 1.5; // Example surcharge for rocky terrain 
        logger.info("Adjusted cost for RockyTerrain: original cost = " + originalCost + ", adjusted cost = " + adjustedCost); 
        return adjustedCost; 
    } 
} 