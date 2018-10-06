package nvs.htl.at.webcamapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import android.os.StrictMode



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        configureVideoView()
    }

    private fun configureVideoView() {
        videoView.setVideoPath(getVideopath())
        videoView.start()
    }

    private fun getVideopath() = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5375&design=v3&c0=0&c2=1&lg=en&s=0&lc=5132")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
            .get()
            .getElementById("fer_video")
            .getElementsByTag("source")
            .first()
            .attr("src")
}
