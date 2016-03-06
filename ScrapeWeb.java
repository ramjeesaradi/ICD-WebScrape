
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class ScrapeWeb {
    	static String getHTML(String urlSt) throws IOException {
        URL url = null;
        try {
            url = new URL(urlSt);
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
//        System.out.println(str);
        return(str);
    }
    	static String getICD10(String pg){
    		
    		String ICD10 = null;
//    		pg = pg.replaceAll("[\t\n\r]", "");
//    		System.out.println(pg);
    		System.out.println(pg.contains("ICD-10-PCS code "));
    		Pattern p = Pattern.compile(".*ICD-10-PCS code ([^\\s]+)\".*",Pattern.DOTALL);
    		
    		Matcher m = p.matcher(pg);
    		m.find();
    		ICD10 = m.group(1);
    		
			return ICD10;
    		
    	}
    public static void main(String[] args) throws IOException {
    	String addr = "http://www.icd10data.com/Convert/";
    	String ICD9 = "51.24";
    	String pg = getHTML("http://www.icd10data.com/Convert/51.24");
    	String ICD10 = getICD10(pg);
    	System.out.println(ICD10);
    }
}
