import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) throws Exception {
        while(true) scrap();
    }

    //scrapping the link from the website
    private static void scrap() throws Exception {
        String link = "https://webtv.feratel.com/webtv/?cam=5375&design=v3&c0=0&c2=1&lg=en&s=0&lc=5132";
        log(Jsoup.connect(link)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                .get()
                .getElementById("fer_video")
                .getElementsByTag("source")
                .first()
                .attr("src"));
    }

    //logging the links + timestamp into a logfile
    private static void log(String message) throws Exception {
        new BufferedWriter(new FileWriter("webcamlogger.txt", true))
                .append(LocalDateTime.now().toString() + " " + message + "\n")
                .close();
        System.out.println(LocalDateTime.now().toString() + " " + message);
    }
}
