package codec.axun.com.kotlinfight

import android.util.Log
import org.jsoup.Jsoup


/**
 * Created by Administrator on 2018/3/26.
 */
class IMainXlmf:IMainModule {


    override fun getImagesByJsoup(url: String): List<XiaoHua> {
        var items = arrayListOf<XiaoHua>()
        Log.d("Html", "开始请求")
        Thread(object : Runnable {
            override fun run() {
                Log.d("Html","开启线程")
                val doc = Jsoup.connect(url).get()

                val els = doc.select("div.item");

                for (el in els){
                    Log.d("Html",el.select("div").toString())
                }
            }

        }).start()
        return items
    }
}