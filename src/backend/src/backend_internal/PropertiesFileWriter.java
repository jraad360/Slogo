package backend_internal;

import java.io.*;
import java.net.URL;
import java.util.*;

public class PropertiesFileWriter {

    //    https://stackoverflow.com/questions/22370051/how-to-write-values-in-a-properties-file-through-java-code
    private static void saveProperties(Properties p, String fileName) throws IOException {
        p.store(new FileOutputStream(new File(fileName)), null);
    }

    /**
     * Would be used if you want to load properties from a property file into a Properties object. All the property
     * pairs from the file would be added to the Properties object p. None of the previous contents of p would be deleted
     * @param p - property you want to write to file
     * @param fileName - name of file you want to update p with
     * @throws IOException
     */
    public static void loadProperties(Properties p, String fileName)throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL f = classLoader.getResource(fileName);
        FileInputStream fr = new FileInputStream(new File(fileName));
        p.load(fr);
        fr.close();
    }

    /**
     * Would erase all the contents of a property file.
     * @param fileName - name of file to be wiped
     * @throws IOException
     */
    public static void clearFileProperties(String fileName) throws IOException {
        saveProperties(new Properties(), fileName);
    }

    /**
     * Takes the elements from inside the Properties object p and adds them to the existing elements in the property file
     * @param key - key of property to be added to file
     * @param value - value of property to be stored in file
     * @param fileName - properties file you wish to update
     * @throws IOException
     */
    public static void addPropertiesToFile(String key, String value, String fileName) throws IOException {
        Properties p = new Properties();
        loadProperties(p, fileName);
        p.setProperty(key, value);
        saveProperties(p, fileName);
    }

    /**
     * Returns the properties of a property file as a map
     * @param filename - file whose properties you want to retrieve
     * @return
     */
    public static Map<String, String> getPropertiesMap(String filename) throws IOException{
        Map<String, String> map = new LinkedHashMap();
        Properties p = new Properties();
        loadProperties(p, filename);
        Set<String> keys = p.stringPropertyNames();
        for (String k : keys) {
            map.put(k, p.getProperty(k));
        }
        return map;
    }

    /**
     * Turns the contents of a file into a String.
     * @param file - file
     * @return result
     */
    // utility function that reads given file and returns its entire contents as a single string
    public static String readFileToString (File file) {
        try {
            final var END_OF_FILE = "\\z";
            var input = new Scanner(file);
            input.useDelimiter(END_OF_FILE);
            var result = input.next();
            input.close();
            return result;
        }
        catch(IOException e){
            return "";
        }
    }
}
