package edu.curtin.app; 
 
import java.util.List; 
import java.util.ArrayList; 
import java.util.logging.Logger; 
 
public class Grid { 
    private static final Logger logger = Logger.getLogger(Grid.class.getName()); 
    private int width; 
    private int height; 
    private GridSquare[][] squares; 
 
    public Grid(int width, int height) { 
        this.width = width; 
        this.height = height; 
        squares = new GridSquare[height][width]; 
        logger.info("Initialized Grid with dimensions: " + width + "x" + height); 
    } 
 
    public GridSquare getSquare(int row, int col) { 
        if (isValidPosition(row, col)) { 
            return squares[row][col]; 
        } else { 
            logger.warning("Attempted to access invalid grid position: (" + row + ", " + col + ")"); 
            return null; 
        } 
    } 
 
    public int getHeight() { 
        return height; 
    } 
 
    public int getWidth() { 
        return width; 
    } 
 
    public List<GridSquare> getAllSquares() { 
        List<GridSquare> allSquares = new ArrayList<>(); 
        for (int row = 0; row < height; row++) { 
            for (int col = 0; col < width; col++) { 
                allSquares.add(squares[row][col]); 
            } 
        } 
        return allSquares; 
    } 
 
    public void setSquare(int row, int col, GridSquare square) { 
        if (isValidPosition(row, col)) { 
            squares[row][col] = square; 
            logger.info("Set GridSquare at position: (" + row + ", " + col + ")"); 
        } else { 
            logger.warning("Attempted to set invalid grid position: (" + row + ", " + col + ")"); 
        } 
    } 
 
    private boolean isValidPosition(int row, int col) { 
        return row >= 0 && row < height && col >= 0 && col < width; 
    } 
}