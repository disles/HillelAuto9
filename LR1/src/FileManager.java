/**
 * Created by original on 01.03.2017.
 */


import java.io.*;

import java.util.Arrays;
import java.util.Scanner;


public class FileManager {
    public static void main(String[] args)throws IOException{
        String[] allowedCommands = {"crf", "delf", "renf", "--help"};
        if (args.length < 1 || !cmdFound(allowedCommands,args[0])){
            errorText();
        }
        else {
            if (args[0].contains("--help")){
                getHelp();
            }
            if (args[0].equalsIgnoreCase("crf")) {
                if (args.length < 2) {
                    errorText();
                }
                else {
                    String filename = args[1];
                    createFile(filename);
                }
            }
            if (args[0].equalsIgnoreCase("delf")) {
                if (args.length < 2) {
                    errorText();
                }
                else {
                    String filename = args[1];
                    deleteFile(filename);
                }
            }
        }
    }
    public static void createFile(String filePath) throws IOException{
        if (filePath == "") {
            filePath = getPathInput();
        }
        System.out.println("Creating File: " + filePath);
        File file = new File(filePath);
        boolean result  = file.createNewFile();
        resultMessage(result);
        if (result) {
            System.out.println(filePath + " has been created");
        }
        else {
            System.out.println(filePath + " already exists");
        }
    }
    public static void deleteFile(String filePath)throws IOException{
        if (filePath == "") {
            filePath = getPathInput();
        }
        File file = new File(filePath);
        boolean result = file.delete();
        resultMessage(result);
        if (result) {
            System.out.println(filePath + " has been deleted");
        } else {
            System.out.println(filePath + " hasn't been found or can't be deleted");
        }
    }

    public static void renameFile(String sourceFile, String destinationFile ) throws IOException{
        if (sourceFile == "" || destinationFile == "") {
            System.out.println("Type in source file to rename");
            sourceFile = getPathInput();
            System.out.println("Type in destination file to rename to");
            destinationFile = getPathInput();
        }
        File fileSrc = new File(sourceFile);
        File fileDst = new File(destinationFile);
        boolean result = fileSrc.renameTo(fileDst);
        resultMessage(result);
        if (result) {
            System.out.println(fileSrc + " has been renamed to " + fileDst);
        }
        else {
            System.out.println(fileSrc + " hasn't been renamed");
        }
    }
    public static void findWords(String filePath, String word) throws IOException{
        if (filePath == ""|| word == ""){
            System.out.println("Please enter file to search words in");
            filePath = getPathInput();
            System.out.println("Please enter desired word to search");
            word = getWordInput();
        }
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(new FileInputStream(file))){;
            int count = 0;
            while (scanner.hasNext()) {
                String fileWord = scanner.next();
                if (fileWord.contains(word)){
                    count++;
                }
            }
            resultMessage(true);
            System.out.println("Word \"" + word + "\" meets " + count + " times in file " + filePath);
            }
    }
    public static void replaceWords(String filePath,String srcWord, String dstWord) throws IOException {
        if (filePath == "" || srcWord == "" || dstWord == "") {
            System.out.println("Please enter file to replace words in");
            filePath = getPathInput();
            System.out.println("Please enter word to rename");
            srcWord = getWordInput();
            System.out.println("Please enter word to rename to");
            dstWord = getWordInput();
        }
        try{
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";

            while((line = reader.readLine()) != null){
                oldtext += line + "\r\n";
            }
            reader.close();

            String replacedText  = oldtext.replaceAll(srcWord, dstWord);
            FileWriter writer = new FileWriter(filePath);
            writer.write(replacedText);
            writer.close();

            resultMessage(true);
            System.out.println("Word \"" + srcWord + "\" have been changed to word \"" + dstWord + "\" in " + filePath);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public static String getPathInput() throws IOException{
        showFilePathTip();
        BufferedReader reader = initReader();
        String filePath = reader.readLine();
        return filePath;
    }
    public static String getWordInput() throws IOException{
        BufferedReader reader = initReader();
        String word = reader.readLine();
        return word;
    }
    public static void errorText(){
        System.out.println("Usage: FileManager <command> <filePath> <extraParams> \n" +
                "for more info run with --help flag");
        System.exit(1);
    }
    public static void getHelp(){
        System.out.println("\n Available commands: \n");
        //CREATE
        System.out.println("crf - creates file");
        //DELETE
        System.out.println("delf - deletes file");
        //RENAME
        System.out.println("renf - renames file ");
    }
    public static boolean cmdFound(String[] allowedCommands, String command)
    {
        return (Arrays.asList(allowedCommands).contains(command));
    }
    public static void showFilePathTip(){
        if (isMac() || isUnix()){
            System.out.println("Enter FilePath in following format /Documents/file.txt");
        }
        else if (isWindows()){
            System.out.println("Enter FilePath in following format C:\\Users\\username\\Documents\\file.txt");
        }
        else {
            System.out.println("Your system is not supported, Use Linux, Mac or Windows instead");
            System.exit(1);
        }
    }
    public static boolean isWindows(){
        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.indexOf( "win" ) >= 0);
    }
    public static boolean isMac(){
        String os = System.getProperty("os.name").toLowerCase();
        //Mac
        return (os.indexOf( "mac" ) >= 0);
    }
    public static boolean isUnix (){
        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
    }
    public static BufferedReader initReader(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader;
    }
    private static void resultMessage(boolean result){
        if (result) {
            System.out.println("SUCCESS!!!");
        }
        else{
            System.out.println("FAILURE");
        }
    }
}

