/**
 * Created by hp on 4/16/2015.
 */
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class ScrapeWeb {
    public static void main(String[] args) throws IOException {
        URL url = null;
        try {
            url = new URL("http://postyourproblem.in");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        Matcher m = p.matcher(con.getContentType());
/* If Content-Type doesn't match this pre-conception, choose default and
 * hope for the best. */
        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        Reader r = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        String str = buf.toString();
        System.out.println(str);
    }
}
