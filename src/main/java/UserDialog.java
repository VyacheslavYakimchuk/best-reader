import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by TeSla on 26.12.2014.
 */
public class UserDialog {
    private String command = "";
    private String currentMessage = "";
    private Scanner scanner;
    private FileReader fileReader;
    private boolean canTalk = true;
    private static final Logger logger = Logger.getLogger(FileReader.class.toString());

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void openFile(String fileName) {
        scanner = new Scanner(System.in);
        try {
            fileReader.openFile(fileName);
            currentMessage = fileReader.getNextLine();
        } catch (FileNotFoundException e) {
            currentMessage = "Could not open file " + fileName + ". You can switch to another one or close the app.";
            canTalk = false;
        } finally {
            logger.info(currentMessage);
        }
    }

    public void start() {
        while (!command.equals("close")) {
            command = scanner.nextLine();
            if (command.contains("changeFile")) {
                String[] commandParts = command.split("=");
                try {
                    fileReader.close();
                    fileReader.openFile(commandParts[1]);
                    currentMessage = fileReader.getNextLine();
                    canTalk = true;
                } catch (Exception e) {
                    currentMessage = "Specify command correctly: changeFile=[Full file path]";
                }
            }
            if (command.equals("stopTalking")) {
                canTalk = false;
            }
            if (command.equals("startTalking")) {
                canTalk = true;
            }
            if (command.equals("") && canTalk == true) {
                currentMessage = fileReader.getNextLine();
                if (currentMessage == null) {
                    currentMessage = "The file was copletely readen. Choose another one.";
                }
            }
            if (command.equals("exit")) {
                currentMessage = fileReader.getLastLine();
            }
            if (!command.equals("close")) {
                logger.info(currentMessage);
            }
        }
        fileReader.close();
    }
}

