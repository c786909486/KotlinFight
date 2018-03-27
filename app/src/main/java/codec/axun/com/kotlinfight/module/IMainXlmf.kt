package codec.axun.com.kotlinfight.module

import android.util.Log
import codec.axun.com.kotlinfight.bean.Picture
import org.jsoup.Jsoup


/**
 * Created by Administrator on 2018/3/26.
 */
class IMainXlmf: IMainModule {


    override fun getImagesByJsoup(url: String): List<Picture> {
        var items = arrayListOf<Picture>()
        Log.d("Html", "开始请求")

            Log.d("Html","开启线程")
            val doc = Jsoup.connect(url).get()
            val els = doc.select("div.item")
            for (el in els){
                val pic= Picture()
                pic.url = el.select("img").attr("src").toString()
                Log.d("url",el.select("img").attr("src").toString())
                pic.title = el.select("h3").text()
                Log.d("title",el.select("h3").text())
                pic.date = el.select("p.calendar").text()
                Log.d("date",el.select("p.calendar").text())
                pic.location = el.select("p.location").text()
                Log.d("location",el.select("p.location").text())
                items.add(pic)
            }
        return items
    }
}