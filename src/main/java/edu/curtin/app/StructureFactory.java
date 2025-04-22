package edu.curtin.app; 
 
import java.util.logging.Level; 
import java.util.logging.Logger; 
 
public class StructureFactory { 
    private static final Logger logger = Logger.getLogger(StructureFactory.class.getName()); 
 
    public Structure createStructure(int floors, FoundationType foundation, MaterialType material) { 
        try { 
            switch (material) { 
                case WOOD: 
                    logger.info("Creating WoodStructure with " + floors + " floors and foundation: " + foundation); 
                    return new WoodStructure(floors, foundation); 
                case STONE: 
                    logger.info("Creating StoneStructure with " + floors + " floors and foundation: " + foundation); 
                    return new StoneStructure(floors, foundation); 
                case BRICK: 
                    logger.info("Creating BrickStructure with " + floors + " floors and foundation: " + foundation); 
                    return new BrickStructure(floors, foundation); 
                case CONCRETE: 
                    logger.info("Creating ConcreteStructure with " + floors + " floors and foundation: " + foundation); 
                    return new ConcreteStructure(floors, foundation); 
                default: 
                    String errorMessage = "Invalid material type: " + material; 
                    logger.severe(errorMessage); 
                    throw new IllegalArgumentException(errorMessage); 
            } 
        } catch (IllegalArgumentException e) { 
            logger.log(Level.SEVERE, "Invalid argument provided: {0}", e.getMessage()); 
            e.printStackTrace(); 
            throw e; 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Unexpected error while creating structure: {0}", e.getMessage()); 
            e.printStackTrace(); 
            throw e; 
        } 
    } 
}