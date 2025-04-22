package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class ContaminatedZoneDecorator extends StructureDecorator { 
    private static final Logger logger = Logger.getLogger(ContaminatedZoneDecorator.class.getName()); 
 
    public ContaminatedZoneDecorator(Structure structure) { 
        super(structure); 
        logger.info("ContaminatedZoneDecorator created for structure: " + structure); 
    } 
 
    @Override 
    public double getCost() { 
        double originalCost = structure.getCost(); 
        double adjustedCost = originalCost * 1.3; // Example multiplier for contamination 
        logger.info("Adjusted cost for ContaminatedZone: original cost = " + originalCost + ", adjusted cost = " + adjustedCost); 
        return adjustedCost; 
    } 
} 