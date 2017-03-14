import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HillelFileWriter {

    private Scanner scanner;
    private FileWriter fw;
    private BufferedWriter bw;

    public HillelFileWriter() {
        this.scanner = new Scanner(System.in);
    }

    public String writeToFile(String fileName, String content) {
        File file = new File(fileName);

        boolean exists = exists(file);

        if (exists) {
            System.out.println("File " + fileName + " exists, writing data...");
            writeToFile(file, content);
        } else {
            System.out.println("File " + fileName + " Doesn't exist, create it? (y/n)");
            String userInput = scanner.nextLine();
            if ("y".equals(userInput)) { //добавить возможность принимать ответ без учета регистра
                try {
                    file.createNewFile();
                    writeToFile(file, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("n".equals(userInput)) {
                System.out.println("Your answer is 'No', File won't be created. Bye!");
            }
        }
        return "Done!";
    }
    private boolean exists(File file) {
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
    private void writeToFile(File file, String content) {
        try {
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}