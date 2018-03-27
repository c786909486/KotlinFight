package codec.axun.com.kotlinfight

/**
 * Created by Administrator on 2018/3/26.
 */
interface IMainView {
    //显示加载框
    fun showProgress()
    //隐藏加载框
    fun hideProgress()
    //显示数据
    fun showData(items:List<XiaoHua>)


}