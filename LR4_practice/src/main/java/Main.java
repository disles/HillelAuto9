import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by original on 13.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        HillelFileWriter writer = new HillelFileWriter();
        if (args.length == 2){
            writer.writeToFile(args[0],args[1]);
        }
        else if (args.length == 0){
            try {
                String filePath = getPathInput();
                String content  = getContent();
                writer.writeToFile(filePath,content);
            }
            catch (IOException e){
                e.getStackTrace();
            }
        }
        else {
            errorMessage();
        }
    }
    public static String getPathInput() throws IOException {
        showFilePathTip();
        BufferedReader reader = initReader();
        String filePath = reader.readLine();
        return filePath;
    }
    public static String getContent() throws IOException {
        BufferedReader reader = initReader();
        System.out.println("Enter Text you want to add to file");
        String content = reader.readLine();
        return content;
    }

    public static void errorMessage(){
        System.out.println("command line usage: java -jar write.jar <filePath> <text>");
        System.out.println("interactive: java -jar write.jar");
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
    public static BufferedReader initReader(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader;
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
