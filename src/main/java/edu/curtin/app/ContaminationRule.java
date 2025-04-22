package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class ContaminationRule implements ZoningRule { 
    private static final Logger logger = Logger.getLogger(ContaminationRule.class.getName()); 
    private boolean isPresent; 
 
    public ContaminationRule(boolean isPresent) { 
        this.isPresent = isPresent; 
        logger.info("ContaminationRule created with presence: " + isPresent); 
    } 
 
    @Override 
    public boolean isValidFor(Structure s) { 
        // Assume all structures are valid for simplicity 
        logger.info("Checking structure against ContaminationRule with presence: " + isPresent); 
        return true; 
    } 
 
    @Override 
    public String getDescription() { 
        return "Contamination present: " + isPresent; 
    } 
}