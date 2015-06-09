package SpiderGetUrl;
import java.lang.*;
import java.net.*;
import java.util.regex.*;
import SpiderIO.SpiderWR;

public class SpiderGetUrl {
    public static void main(String[] args) throws Exception {
        //Demo
        getContent a = new getContent("http://php.labs/rin.jpg","([A-Za-z0-9-]*.(jpg|png|tiff))");
    }
}
class getContent {
    getContent(String url, String regex) throws Exception {
        //Judge -- IF Fail To Match.
        this.pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(url);
        //while(match.find()) System.out.println(match.group());
        if (!match.find()) {
            System.out.println("Skip this link!Code #3 ");
            return;
        }
        this.filename = match.group();
        //Get Picture INFO

        //HTTP connection (without SSL/TLS)
        setUrl(url);
        HttpURLConnection connection = (HttpURLConnection) (new URL(this.url)).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty(
                "User-agent", "Mozilla/5.0 (MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        //COOKIE
        //REF
        connection.setConnectTimeout(3 * 1000);
        connection.setDoOutput(true);
        connection.connect();

        new SpiderWR(connection, filename);


    }

    public void setUrl(String url) {this.url = url;}

    //public void Print(Object ss){System.out.println(ss);}
    private String url;
    private Pattern pattern;
    private String filename;
}
