import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SystemTools {
    private static Pattern titleAuthor1 = Pattern.compile("\\s*The Project Gutenberg EBook of ([^,]*)(,? by (.*))");
    private static Pattern titleAuthor2 = Pattern.compile("\\s*Project Gutenberg.s ([^,]*)(,? by (.*))");
    private static Pattern titleNoAuthor1 = Pattern.compile("\\s*The Project Gutenberg EBook of (.*)");
    private static Pattern titleNoAuthor2 = Pattern.compile("\\s*Project Gutenberg.s (.*)");

    private static Pattern thisEbook = Pattern.compile("This eBook");

    public static String findTitle(String t) {
        Scanner s = new Scanner(t);

        // first line
        String line;
        while ((line = s.nextLine()).length() < 10);

        Matcher m1 = titleAuthor1.matcher(line);
        Matcher m2 = titleAuthor2.matcher(line);
        Matcher m3 = titleNoAuthor1.matcher(line);
        Matcher m4 = titleNoAuthor2.matcher(line);

        if (m1.find()) {
            return m1.group(1).trim();
        } else if (m2.find()) {
            return m2.group(1).trim();
        } else if (m3.find()) {
            return m3.group(1).trim();
        } else if (m4.find()) {
            return m4.group(1).trim();
        } else {
            return "Title Unknown";
        }
    }

    public static String findAuthor(String t) {
        Scanner s = new Scanner(t);

        // first line
        String line;
        while ((line = s.nextLine()).length() < 10);

        Matcher m1 = titleAuthor1.matcher(line);
        Matcher m2 = titleAuthor2.matcher(line);

        if (m1.find()) {
            return m1.group(3).trim();
        } else if (m2.find()) {
            return m2.group(3).trim();
        } else {
            return "Author Unknown";
        }
    }


    static String download(URL url) {
        try {
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(4000);
            conn.setConnectTimeout(4000);
            String all = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().collect(Collectors.joining("\n"));
            return all;
        } catch (MalformedURLException e) {
            return "Error: bad url [" + url + "]";
        } catch (IOException e) {
            return "Error: could not open url [" + url + "]";
        }
    }
}
