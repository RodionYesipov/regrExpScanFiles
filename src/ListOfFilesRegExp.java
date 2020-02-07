import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ListOfFilesRegExp {

    private static List<String> findWordInDirectory(File file, String word, List<String> result) {
        List<File> files = Arrays.asList(file.listFiles());
        for (File f : files) {
            if (!f.isDirectory()) {
                if (scanFile(f, word)) {
                    result.add(f.getAbsolutePath());
                }
            } else {
                findWordInDirectory(f, word,result);
            }
        }
        return result;
    }

    private static boolean scanFile(File inFile, String word) {
        try {
            Scanner scanner = new Scanner(inFile);
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().trim().toLowerCase().contains(word.toLowerCase())) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean outputFileList (String inDirectory, String needWord, String outputDirectory) throws IOException {
        List<String> result = new ArrayList<>();
        result = findWordInDirectory(new File("D:\\test"), needWord,result);

        FileWriter writer = new FileWriter(outputDirectory);
        for(String str: result) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        return false;
    }
    ///////////////////////////
    ///////////////////////////
    public static void main(String[] args) throws IOException {
        outputFileList("D:\\test","q0tower.q0tower","D:\\test\\output.txt");
    }
}