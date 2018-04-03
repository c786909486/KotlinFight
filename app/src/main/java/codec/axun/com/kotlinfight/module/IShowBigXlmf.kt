package codec.axun.com.kotlinfight.module

import android.app.Activity
import codec.axun.com.kotlinfight.bean.Picture

class IShowBigXlmf(private val activity: Activity) : IShowBigModule {
    override fun getPictureData(): Picture {
      return  activity.intent.getSerializableExtra("data") as Picture
    }
}