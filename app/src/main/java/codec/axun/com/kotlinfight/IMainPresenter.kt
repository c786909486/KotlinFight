package codec.axun.com.kotlinfight

import android.util.Log

/**
 * Created by Administrator on 2018/3/26.
 */

class IMainPresenter(internal var view: IMainView) {
    internal var module: IMainModule

    fun getNetData(url:String){
        var items = module.getImagesByJsoup(url)
        showUIData(items)
        Log.d("Html","网络请求")
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

    private fun showUIData(items:List<XiaoHua>){
        view.showData(items)
    }

}
