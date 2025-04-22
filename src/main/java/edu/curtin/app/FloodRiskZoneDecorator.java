package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class FloodRiskZoneDecorator extends StructureDecorator { 
    private static final Logger logger = Logger.getLogger(FloodRiskZoneDecorator.class.getName()); 
    private double risk; 
 
    public FloodRiskZoneDecorator(Structure structure, double risk) { 
        super(structure); 
        this.risk = risk; 
        logger.info("FloodRiskZoneDecorator created for structure: " + structure + " with risk level: " + risk); 
    } 
 
    @Override 
    public double getCost() { 
        double originalCost = structure.getCost(); 
        double adjustedCost = originalCost * (1 + risk); // Cost increases with flood risk 
        logger.info("Adjusted cost for FloodRiskZone: original cost = " + originalCost + ", adjusted cost = " + adjustedCost); 
        return adjustedCost; 
    } 
} 