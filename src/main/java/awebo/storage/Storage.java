package awebo.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The {@code Storage} class provides functionality for writing a list of objects to a file.
 * This class is abstract and cannot be instantiated.
 */
public abstract class Storage {

    /**
     * Writes the given list of objects to a file, each on a new line.
     *
     * @param list The list of objects to be written to the file.
     * @param filename The name of the file where the list will be stored.
     */
    public static void writeListToFile(List<?> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Object item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
