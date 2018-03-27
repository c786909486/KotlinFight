package codec.axun.com.kotlinfight.presenter

import android.util.Log
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.module.IMainModule
import codec.axun.com.kotlinfight.module.IMainXlmf
import codec.axun.com.kotlinfight.view.IMainView

/**
 * Created by Administrator on 2018/3/26.
 */

class IMainPresenter(internal var view: IMainView) {
    internal var module: IMainModule

    fun getNetData(url:String){
        Thread(Runnable {
            val items = module.getImagesByJsoup(url)
            showUIData(items)
            Log.d("Html","网络请求")
        }).start()

    }

    init {
        module = IMainXlmf()
    }

    private fun showProgress(){
        view.showProgress()
    }

    private fun hideProgress(){
        view.hideProgress()
    }

    private fun showUIData(items:List<Picture>){
        view.showData(items)
    }

}
