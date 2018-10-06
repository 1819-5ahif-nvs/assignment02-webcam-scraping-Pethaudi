package at.htl.nvs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HttpServer server;

        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/video", new VideoHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class VideoHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String resp = "Error";
            try {
                resp = generateResponse();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            t.sendResponseHeaders(200, resp.length());
            OutputStream os = t.getResponseBody();
            os.write(resp.getBytes());
            os.close();
        }

        private String generateResponse() throws IOException {
            String link = "https://webtv.feratel.com/webtv/?cam=5375&design=v3&c0=0&c2=1&lg=en&s=0&lc=5132";
            return Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                    .get()
                    .getElementById("fer_video")
                    .getElementsByTag("source")
                    .first()
                    .attr("src");
        }
    }
}
