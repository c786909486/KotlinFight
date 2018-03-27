package codec.axun.com.kotlinfight

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(),IMainView {
    private var page = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = IMainPresenter(this)
        presenter.getNetData(KeyCenter.url+page)
    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showData(items: List<XiaoHua>) {

    }



}
