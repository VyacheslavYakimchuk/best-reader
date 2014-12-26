import java.io.*;

import org.apache.log4j.Logger;

/**
 * Created by TeSla on 25.12.2014.
 */
public class Reader {
    private BufferedReader br = null;
    static final Logger logger = Logger.getLogger(Reader.class.toString());

    void read(String fileName) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in, "windows-1251"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void close() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void print(String message) {
        logger.info(message);
    }

    void nextLine() {
        String currentString;
        try {
            if ((currentString = br.readLine()) != null) {
                logger.info(currentString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
