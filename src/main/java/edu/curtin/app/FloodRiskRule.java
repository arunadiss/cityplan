package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class FloodRiskRule implements ZoningRule { 
    private static final Logger logger = Logger.getLogger(FloodRiskRule.class.getName()); 
    private double risk; 
 
    public FloodRiskRule(double risk) { 
        this.risk = risk; 
        logger.info("FloodRiskRule created with risk level: " + risk); 
    } 
 
    @Override 
    public boolean isValidFor(Structure s) { 
        // Assume all structures are valid for simplicity 
        logger.info("Checking structure against FloodRiskRule with risk level: " + risk); 
        return true; 
    } 
 
    @Override 
    public String getDescription() { 
        return "Flood risk: " + risk; 
    } 
}