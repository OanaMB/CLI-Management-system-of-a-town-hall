package org.example;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.io.*;

public class FunctiiFisiere {

    /* citeste fisier */
    public static void citesteFisier(File fileInput, PrintWriter printWriter,ManagementPrimarie managementPrimarie) throws FileNotFoundException {
        try {
            Scanner myReader = new Scanner(fileInput);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] arguments = line.split("; ");
                /* alegem ce functie trebuie indeplinita acum bazandu-ne pe arguments[0] */
                ManagementPrimarie functionalitati = new ManagementPrimarie();
                functionalitati.functionalitati(arguments, managementPrimarie, printWriter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
