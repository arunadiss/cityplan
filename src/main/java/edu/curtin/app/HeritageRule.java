package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class HeritageRule implements ZoningRule { 
    private static final Logger logger = Logger.getLogger(HeritageRule.class.getName()); 
    private MaterialType material; 
 
    public HeritageRule(MaterialType material) { 
        this.material = material; 
        logger.info("HeritageRule created for material: " + material); 
    } 
 
    @Override 
    public boolean isValidFor(Structure s) { 
        boolean isValid = s.getMaterial() == material; 
        if (isValid) { 
            logger.info("Structure is valid for HeritageRule with material: " + material); 
        } else { 
            logger.warning("Structure is not valid for HeritageRule with material: " + material); 
        } 
        return isValid; 
    } 
 
    @Override 
    public String getDescription() { 
        return "Heritage rule for material: " + material; 
    } 
}