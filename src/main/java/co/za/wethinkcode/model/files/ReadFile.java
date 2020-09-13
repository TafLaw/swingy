package co.za.wethinkcode.model.files;

import java.io.*;
import java.util.*;

public class ReadFile {
    public static ArrayList<String> allHeroes = new ArrayList<String>();
    public void readFromFile(String filename)
    {
        String line;

        try {
            File file = new File(filename);
            Scanner scanFile = new Scanner(file);
            while(scanFile.hasNextLine())
            {
                line = scanFile.nextLine();
                allHeroes.add(line);
            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("error: File not found");
            System.exit(1);
        }
    }
}