package com.mycompany.commandlinestore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Marko
 */
public class CommandLineStore {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException{
        InputOutput io = new InputOutput();
        io.itemList.loadList();
        Scanner scanner = new Scanner(System.in);
        String string = new String();
        int run = 0;
        while(run!=3){
        string = scanner.nextLine();
        run = io.commandInput(string);
        }
    }
}