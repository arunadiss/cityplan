package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class SwampyTerrainCostDecorator extends StructureDecorator { 
    private static final Logger logger = Logger.getLogger(SwampyTerrainCostDecorator.class.getName()); 
 
    public SwampyTerrainCostDecorator(Structure structure) { 
        super(structure); 
        logger.info("SwampyTerrainCostDecorator created for structure: " + structure); 
    } 
 
    @Override 
    public double getCost() { 
        double originalCost = structure.getCost(); 
        double adjustedCost = originalCost * 1.2; // Example surcharge for swampy terrain 
        logger.info("Adjusted cost for SwampyTerrain: original cost = " + originalCost + ", adjusted cost = " + adjustedCost); 
        return adjustedCost; 
    } 
} 