package edu.curtin.app; 
 
public enum TerrainType { 
    FLAT, SWAMPY, ROCKY 
} 
 
public enum MaterialType { 
    WOOD, CONCRETE, STONE, BRICK 
} 
 
public enum FoundationType { 
    SLAB, STILTS 
} 
 
public interface ZoningRule { 
    boolean isValidFor(Structure s); 
    String getDescription(); 
}