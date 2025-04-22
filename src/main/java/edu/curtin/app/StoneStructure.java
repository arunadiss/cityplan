package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class StoneStructure implements Structure { 
    private static final Logger logger = Logger.getLogger(StoneStructure.class.getName()); 
    private int floors; 
    private FoundationType foundation; 
    private MaterialType material; 
 
    public StoneStructure(int floors, FoundationType foundation) { 
        this.floors = floors; 
        this.foundation = foundation; 
        this.material = MaterialType.STONE; 
        logger.info("StoneStructure created with " + floors + " floors and foundation type: " + foundation); 
    } 
 
    @Override 
    public double getCost() { 
        double cost = floors * 1800; // Example cost calculation 
        logger.info("Calculated cost for StoneStructure: " + cost); 
        return cost; 
    } 
 
    @Override 
    public int getFloors() { 
        return floors; 
    } 
 
    @Override 
    public MaterialType getMaterial() { 
        return material; 
    } 
 
    @Override 
    public FoundationType getFoundation() { 
        return foundation; 
    } 
} 