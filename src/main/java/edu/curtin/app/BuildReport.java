package edu.curtin.app; 
 
public class BuildReport { 
    private int totalStructures; 
    private double totalCost; 
    private boolean[][] successMap; 
 
    public BuildReport(int totalStructures, double totalCost, boolean[][] successMap) { 
        this.totalStructures = totalStructures; 
        this.totalCost = totalCost; 
        this.successMap = successMap; 
    } 
 
    public int getTotalStructures() { 
        return totalStructures; 
    } 
 
    public double getTotalCost() { 
        return totalCost; 
    } 
 
    public boolean[][] getMap() { 
        return successMap; 
    } 
} 