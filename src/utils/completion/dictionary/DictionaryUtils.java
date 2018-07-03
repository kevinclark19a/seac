package utils.completion.dictionary;

import core.application.Controller;
import utils.completion.SearchTree;
import utils.log.Log;

import java.io.*;

public class DictionaryUtils {

    private static final String filename = "dictionary",
            localSysPath = System.getProperty("user.home") + "/.seac/",
            resourcePath = "resources/";

    /**
     *
     * @return
     * @throws IOException
     */
    static InputStream getLocalDictionary() throws IOException {
        return new FileInputStream(new File(localSysPath + filename));
    }

    /**
     *
     * @return
     */
    static InputStream getPackagedDictionary() {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath + filename);
    }

    /**
     *
     * @param tree
     */
    public static void saveDictionary(SearchTree tree) {
        var dirOut = new File(localSysPath);
        var fileOut = new File(localSysPath + filename);

        try {
            dirOut.mkdirs();
            fileOut.createNewFile();
        } catch (final Exception e) {
            Log.ex(e, "Error creating parent directories and file. Current tree not saved.");
            return;
        }

        try (var printer = new PrintStream(fileOut)) {
            tree.getPriorityMap().forEach((key, value) -> printer.println(key + " " + value));
        } catch (final Exception e) {
            Log.ex(e, "Error opening file. Current tree not saved.");
        }

    }
}
