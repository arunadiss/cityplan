package edu.curtin.app; 
 
import java.util.logging.Logger; 
 
public class ConcreteStructure implements Structure { 
    private static final Logger logger = Logger.getLogger(ConcreteStructure.class.getName()); 
    private int floors; 
    private FoundationType foundation; 
    private MaterialType material; 
 
    public ConcreteStructure(int floors, FoundationType foundation) { 
        this.floors = floors; 
        this.foundation = foundation; 
        this.material = MaterialType.CONCRETE; 
        logger.info("ConcreteStructure created with " + floors + " floors and foundation type: " + foundation); 
    } 
 
    @Override 
    public double getCost() { 
        double cost = floors * 2000; // Example cost calculation 
        logger.info("Calculated cost for ConcreteStructure: " + cost); 
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