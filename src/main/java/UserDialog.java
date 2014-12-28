import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by TeSla on 26.12.2014.
 */
public class UserDialog {
    private String command = "";
    private Scanner scanner;
    private FileReader fileReader;
    static final Logger logger = Logger.getLogger(FileReader.class.toString());

    public UserDialog(String fileName) {
        scanner = new Scanner(System.in);
        fileReader = new FileReader();
        try {
            fileReader.openFile(fileName);
            logger.info(fileReader.getNextLine());
        } catch (FileNotFoundException e){
            logger.info("Could not open file " + fileName + ". You can switch to another one or close the app.");
        }
    }

    public void start() {
        boolean canTalk = true;
        while (!command.equals("close")) {
            command = scanner.nextLine();
            if (command.contains("changeFile")) {
                String[] commandParts = command.split("=");
                try {
                    fileReader.openFile(commandParts[1]);
                    logger.info(fileReader.getNextLine());
                } catch (Exception e) {
                    logger.info("Specify command correctly: changeFile=[Full file path]");
                }
            }
            if (command.equals("stopTalking")) {
                canTalk = false;
            }
            if (command.equals("startTalking")) {
                canTalk = true;
            }
            if (command.equals("") && canTalk == true) {
                if (fileReader.getNextLine() == null) {
                    logger.info("The file was copletely readen. Choose another one.");
                } else {
                    logger.info(fileReader.getNextLine());
                }
            }
            if (command.equals("exit")) {
                logger.info(fileReader.getLastLine());
            }
        }
        fileReader.close();
    }
}

