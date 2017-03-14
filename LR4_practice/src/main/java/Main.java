import java.util.Scanner;

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
            Scanner scanner = new Scanner(System.in);

        }
        else {
            errorMessage();
        }
    }
    public static void errorMessage(){
        System.out.println("command line usage: java -jar write.jar <filePath> <text>");
        System.out.println("interactive: java -jar write.jar");
    }
}
