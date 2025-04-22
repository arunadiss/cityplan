package edu.curtin.app; 
 
import java.util.HashSet; 
import java.util.Set; 
import java.util.logging.Logger; 
 
public class GridSquare { 
    private static final Logger logger = Logger.getLogger(GridSquare.class.getName()); 
    private TerrainType terrain; 
    private Set<ZoningRule> zoningRules; 
    private Structure structure; 
 
    public GridSquare(TerrainType terrain, Set<ZoningRule> zoningRules) { 
        this.terrain = terrain; 
        this.zoningRules = zoningRules != null ? zoningRules : new HashSet<>(); 
        logger.info("Created GridSquare with terrain: " + terrain + " and zoning rules: " + this.zoningRules); 
    } 
 
    public TerrainType getTerrain() { 
        return terrain; 
    } 
 
    public Set<ZoningRule> getZoningRules() { 
        return zoningRules; 
    } 
 
    public void setStructure(Structure s) { 
        if (s != null && zoningRules.stream().allMatch(rule -> rule.isValidFor(s))) { 
            this.structure = s; 
            logger.info("Structure set on GridSquare: " + s); 
        } else { 
            logger.warning("Attempted to set invalid structure on GridSquare: " + s); 
        } 
    } 
 
    public Structure getStructure() { 
        return structure; 
    } 
}