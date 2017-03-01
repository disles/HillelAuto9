/**
 * Created by original on 01.03.2017.
 */


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class FileManager {


    public static void main(String[] args) {
        sysNotice();
        String[] allowedCommands = {"crf", "crd", "delf", "deld", "renf", "rend", "--help"};

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

        }
    }

    public static boolean createFile(String filename){
        System.out.print("Creating File: ");
        System.out.println(filename);

        String currentPath = getPath();
        System.out.println("File have been created: " + currentPath + "\\" + filename);
        return true;

    }

    public static String getPath(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        return s;
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
        System.out.println("crd - creates directory - ** NOT IMPLEMENTED YET **  \n");

        //DELETE
        System.out.println("delf - deletes file - ** NOT IMPLEMENTED YET** ");
        System.out.println("deld - deletes directory - ** NOT IMPLEMENTED YET**  \n");

        //RENAME
        System.out.println("renf - renames file - ** NOT IMPLEMENTED YET** ");
        System.out.println("rend - renames directory - ** NOT IMPLEMENTED YET**  \n");
    }
    public static boolean cmdFound(String[] allowedCommands, String command)
    {
        return (Arrays.asList(allowedCommands).contains(command));
    }

    public static void sysNotice(){
        if (isMac() || isUnix())
        {
            System.out.println("Since you are "+ System.getProperty("os.name")+ " user, the program will use bash commands \n");
        }
        if (isWindows()){
            System.out.println("Since you are Windows user, the program will use command line commands. " +
                    "Make sure you are running it via cmd \n");
        }
        else {
            System.out.println("Your system is not supported, Use Linux, Mac or Windows instead \n");
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
}

