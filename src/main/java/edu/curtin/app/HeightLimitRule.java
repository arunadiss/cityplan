package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class HeightLimitRule implements ZoningRule { 
    private static final Logger logger = Logger.getLogger(HeightLimitRule.class.getName()); 
    private int maxFloors; 
 
    public HeightLimitRule(int maxFloors) { 
        this.maxFloors = maxFloors; 
        logger.info("HeightLimitRule created with max floors: " + maxFloors); 
    } 
 
    @Override 
    public boolean isValidFor(Structure s) { 
        boolean isValid = s.getFloors() <= maxFloors; 
        if (isValid) { 
            logger.info("Structure is valid for HeightLimitRule with max floors: " + maxFloors); 
        } else { 
            logger.warning("Structure exceeds height limit of " + maxFloors + " floors"); 
        } 
        return isValid; 
    } 
 
    @Override 
    public String getDescription() { 
        return "Height limit: " + maxFloors + " floors"; 
    } 
}