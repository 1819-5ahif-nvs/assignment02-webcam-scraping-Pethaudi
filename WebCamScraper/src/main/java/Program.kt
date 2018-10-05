import org.jsoup.Jsoup
import java.time.LocalDateTime

fun main(args: Array<String>) {
    scrap()
}

fun scrap() {
    val link = "https://webtv.feratel.com/webtv/?cam=5375&design=v3&c0=0&c2=1&lg=en&s=0&lc=5132"
    val website = Jsoup.connect(link)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
            .get()

    val videolink = website.getElementById("fer_video").getElementsByTag("source").attr("src")
}

fun getLogEntry(s: String) = LocalDateTime.now().toString() + " " + s