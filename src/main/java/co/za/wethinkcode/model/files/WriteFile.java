package co.za.wethinkcode.model.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WriteFile {
    public void writeToFile(ArrayList<String> heroes)
    {
        try {
            BufferedWriter writeMessage = new BufferedWriter(new FileWriter("heroes.txt"));
            for (String hero: heroes) {
                writeMessage.write(hero);
                writeMessage.append('\n');
            }

            writeMessage.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
