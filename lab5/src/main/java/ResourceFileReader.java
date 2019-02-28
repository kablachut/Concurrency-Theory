import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ResourceFileReader {

    public void readFile(String fileName) {
        try {
            // the slash '/' is required
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + fileName), "UTF-8"));
            String line = null;

            while ((line = br.readLine()) != null) {
                // process line
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}