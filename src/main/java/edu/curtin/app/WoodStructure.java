package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class WoodStructure implements Structure { 
    private static final Logger logger = Logger.getLogger(WoodStructure.class.getName()); 
    private int floors; 
    private FoundationType foundation; 
    private MaterialType material; 
 
    public WoodStructure(int floors, FoundationType foundation) { 
        this.floors = floors; 
        this.foundation = foundation; 
        this.material = MaterialType.WOOD; 
        logger.info("WoodStructure created with " + floors + " floors and foundation type: " + foundation); 
    } 
 
    @Override 
    public double getCost() { 
        double cost = floors * 1000; // Example cost calculation 
        logger.info("Calculated cost for WoodStructure: " + cost); 
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