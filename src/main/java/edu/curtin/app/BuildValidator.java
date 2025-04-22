package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class BuildValidator { 
    private static final Logger logger = Logger.getLogger(BuildValidator.class.getName()); 
 
    public ValidationResult validate(GridSquare gridSquare, Structure structure) { 
        try { 
            for (ZoningRule rule : gridSquare.getZoningRules()) { 
                if (!rule.isValidFor(structure)) { 
                    String reason = "Invalid due to zoning rule: " + rule.getDescription(); 
                    logger.warning(reason); 
                    return new ValidationResult(false, reason); 
                } 
            } 
            logger.info("Structure is valid."); 
            return new ValidationResult(true, "Structure is valid."); 
        } catch (Exception e) { 
            logger.severe("Error during validation: " + e.getMessage()); 
            e.printStackTrace(); 
            return new ValidationResult(false, "Validation error occurred."); 
        } 
    } 
} 