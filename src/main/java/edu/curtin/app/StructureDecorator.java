package edu.curtin.app; 
 
public abstract class StructureDecorator implements Structure { 
    protected Structure structure; 
 
    public StructureDecorator(Structure structure) { 
        this.structure = structure; 
    } 
 
    @Override 
    public double getCost() { 
        return structure.getCost(); 
    } 
 
    @Override 
    public int getFloors() { 
        return structure.getFloors(); 
    } 
 
    @Override 
    public MaterialType getMaterial() { 
        return structure.getMaterial(); 
    } 
 
    @Override 
    public FoundationType getFoundation() { 
        return structure.getFoundation(); 
    } 
} 