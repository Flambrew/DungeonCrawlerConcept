package src.gameComponents;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Config.java
 * This class is used to read a .json file
 * @author Andrew Matherne
 */
public class Config {
    JSONParser jParser;
    FileReader settings;
    JSONObject inputValues;
    JSONObject tempObject;

    /**
     * Constructor for Config.java
     * @param file The .json file to be parsed
     * @author Andrew Matherne
     */
    public Config(String file) {
        this.jParser = new JSONParser();
        try {
            this.settings = new FileReader(file);
            this.inputValues = (JSONObject) jParser.parse(settings);
        } catch (Exception e) {
            System.out.println("File " + file + " not found.");
        }
    }

    /**
     * Getter for a value in the parsed .json file
     * @param key Key used to locate information
     * @return Value at specified Key
     * @author Andrew Matherne
     */
    public int getValue(String key) {
        return (int) (long) inputValues.get(key);
    }

    /**
     * Getter for a value in the parsed .json file
     * @param header Header used to locate information
     * @param key Key used to locate information
     * @return Value at specified Header, Key
     * @author Andrew Matherne
     */
    public int getValue(String header, String key) {
        tempObject = (JSONObject) this.inputValues.get(header);
        return (int) (long) tempObject.get(key);
    }
    
    /**
     * Getter for a value in the parsed .json file
     * @param header Header used to locate information
     * @param subHeader Sub-header used to locate information
     * @param key Key used to locate information
     * @return Value at specified Header, SubHeader, Key
     * @author Andrew Matherne
     */
    public int getValue(String header, String subHeader, String key) {
        tempObject = (JSONObject) this.inputValues.get(header);
        tempObject = (JSONObject) this.tempObject.get(subHeader);
        return (int) (long) tempObject.get(key);
    }
}