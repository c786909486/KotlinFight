package codec.axun.com.kotlinfight.module

import codec.axun.com.kotlinfight.bean.Picture

/**
 * Created by Administrator on 2018/3/26.
 */
interface IMainModule {

    fun getImagesByJsoup(url:String):List<Picture>
}