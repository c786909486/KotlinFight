package codec.axun.com.kotlinfight.presenter

import android.app.Activity
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.module.IShowBigModule
import codec.axun.com.kotlinfight.module.IShowBigXlmf
import codec.axun.com.kotlinfight.view.IShowBigView

class IShowBigPresenter(private val activity:Activity,private val view:IShowBigView) {

    private var module:IShowBigModule

    init {
         module = IShowBigXlmf(activity)
    }

     fun showUI(){
        showData(module.getPictureData())
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