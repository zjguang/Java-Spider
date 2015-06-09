package SpiderIO;
import java.io.*;
import java.net.HttpURLConnection;

public class SpiderWR {
    public SpiderWR(HttpURLConnection connection,String filename) throws IOException {
        //Stream2File
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        File image = CreateFile(filename);

        try {
            in = new BufferedInputStream(connection.getInputStream());
            out = new BufferedOutputStream(new FileOutputStream(image));
            byte[] data = new byte[4096]; //buffer size
            int i = 0;
            while ((i = in.read(data)) != -1) out.write(data, 0, i);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //close
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private File CreateFile(String Filename) throws IOException {
        String name = Filename;
        File temp = new File(name);
        if (!temp.createNewFile()) {
            name = "Another-" + name;
            return CreateFile(name);
        } else {
            return temp;
        }
    }
}
