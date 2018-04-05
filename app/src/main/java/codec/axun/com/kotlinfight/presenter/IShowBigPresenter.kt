package codec.axun.com.kotlinfight.presenter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.module.IShowBigModule
import codec.axun.com.kotlinfight.module.IShowBigXlmf
import codec.axun.com.kotlinfight.util.SaveUtil
import codec.axun.com.kotlinfight.view.IShowBigView

class IShowBigPresenter(private val activity:Activity,private val view:IShowBigView) {

    private var module:IShowBigModule

    init {
         module = IShowBigXlmf(activity)
    }

     fun showUI(){
        showData(module.getPictureData())
    }

    fun clickPic( type:Int){
        if (type == 0){
            showSave()
        }else{
            showPicTitle()
        }
    }

    fun downloadPic(context: Context,bitmap:Bitmap?,name:String):String{
       return SaveUtil.saveBitmap(context,bitmap,name)
    }

    private fun showData(data:Picture){
        view.showData(data)
    }

    private fun showPicTitle(){
        view.showImageTitle()
    }

    private fun showSave(){
        view.showSave()
    }
}