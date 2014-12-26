import java.util.Scanner;

/**
 * Created by TeSla on 26.12.2014.
 */
public class Player {
    private String command = "";
    private Scanner scanner;
    private Reader reader;

    public Player(String fileName) {
        scanner = new Scanner(System.in);
        reader = new Reader();
        reader.read(fileName);
        reader.nextLine();
    }

    void play() {
        boolean canTalk = true;
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            if (command.contains("changeFile")) {
                String[] commandParts = command.split("=");
                try {
                    reader.read(commandParts[1]);
                    reader.nextLine();
                } catch (Exception e) {
                    reader.print("Specify command correctly: changeFile=[Full file path]");
                }
            }
            if (command.equals("stopTalking")) {
                canTalk = false;
            }
            if (command.equals("startTalking")) {
                canTalk = true;
            }
            if (command.equals("") && canTalk == true) {
                reader.nextLine();
            }
        }
        reader.close();
    }
}

