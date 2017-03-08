

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by ori on 07.03.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        printMenu();
        performAction();

    }
    public static void printMenu(){
        System.out.println("Enter number from the list to perform following action:");
        System.out.println("1 - Create File");
        System.out.println("2 - Delete File");
        System.out.println("3 - Rename File");
        System.out.println("4 - Find word in file");
        System.out.println("5 - Replace ford in file");
        System.out.println("0 - Exit the program");
    }
    public static void performAction() throws IOException {
        String choice;
        do {
            choice = getUserChoice();
            switch (choice) {
                case "0":
                    break;
                case "1":
                    System.out.println("You have chosen to create file");
                    FileManager.createFile("");
                    printMenu();
                    break;
                case "2":
                    System.out.println("You have chosen to delete file");
                    FileManager.deleteFile("");
                    printMenu();
                    break;
                case "3":
                    System.out.println("You have chosen to rename file");
                    FileManager.renameFile("","");
                    printMenu();
                    break;
                case "4":
                    System.out.println("You have chosen to find word in file");
                    FileManager.findWords("","");
                    printMenu();
                    break;
                case "5":
                    System.out.println("You have chosen to replace word in file");
                    FileManager.replaceWords("","","");
                    printMenu();
                    break;
                default:
                    printMenu();
            }
        }
        while (!choice.equals("0"));
    }

    public static String getUserChoice() throws IOException{
        BufferedReader reader = FileManager.initReader();
        return reader.readLine();
    }

}
