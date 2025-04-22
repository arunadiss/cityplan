package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class BrickStructure implements Structure { 
    private static final Logger logger = Logger.getLogger(BrickStructure.class.getName()); 
    private int floors; 
    private FoundationType foundation; 
    private MaterialType material; 
 
    public BrickStructure(int floors, FoundationType foundation) { 
        this.floors = floors; 
        this.foundation = foundation; 
        this.material = MaterialType.BRICK; 
        logger.info("BrickStructure created with " + floors + " floors and foundation type: " + foundation); 
    } 
 
    @Override 
    public double getCost() { 
        double cost = floors * 1500; // Example cost calculation 
        logger.info("Calculated cost for BrickStructure: " + cost); 
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