package edu.curtin.app; 
 
import java.util.logging.ConsoleHandler; 
import java.util.logging.FileHandler; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.util.logging.SimpleFormatter; 
 
/** 
 * AppLogger is responsible for configuring and providing a logger for the application. 
 */ 
public class AppLogger { 
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName()); 
 
    static { 
        try { 
            // Set the global log level to ALL to capture all log messages 
            logger.setLevel(Level.ALL); 
 
            // Create a console handler with a WARNING level 
            ConsoleHandler consoleHandler = new ConsoleHandler(); 
            consoleHandler.setLevel(Level.WARNING); 
            consoleHandler.setFormatter(new SimpleFormatter()); 
 
            // Create a file handler with an ALL level 
            FileHandler fileHandler = new FileHandler("app%u.log"); 
            fileHandler.setLevel(Level.ALL); 
            fileHandler.setFormatter(new SimpleFormatter()); 
 
            // Add handlers to the logger 
            logger.addHandler(consoleHandler); 
            logger.addHandler(fileHandler); 
 
            logger.info("Logger initialized successfully."); 
        } catch (Exception e) { 
            logger.log(Level.SEVERE, "Failed to initialize logger: {0}", e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Returns the logger instance for the application. 
     * 
     * @return Logger instance 
     */ 
    public static Logger getLogger() { 
        return logger; 
    } 
} 