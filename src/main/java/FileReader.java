import java.io.*;

/**
 * Created by TeSla on 25.12.2014.
 */
public class FileReader {
    private BufferedReader br = null;

    public void openFile(String fileName) throws FileNotFoundException {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getNextLine() {
        String nextLine;
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("IOException" + e);
        }
        return nextLine;
    }

    public String getLastLine() {
        String lastLine = "";
        String currentLine;
        while ((currentLine = getNextLine()) != null) {
            if (currentLine != null) {
                lastLine = currentLine;
            }
        }
        return lastLine;
    }


    public void close() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
