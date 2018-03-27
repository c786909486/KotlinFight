package codec.axun.com.kotlinfight

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import codec.axun.com.kotlinfight.adapter.ShowImageAdapte
import codec.axun.com.kotlinfight.bean.KeyCenter
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.presenter.IMainPresenter
import codec.axun.com.kotlinfight.view.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {
    private var page = 1;
    private var items:ArrayList<Picture> = arrayListOf()
    private var mAdapter:ShowImageAdapte = ShowImageAdapte(this,items)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = IMainPresenter(this)
        presenter.getNetData(KeyCenter.url+page)
//        mAdapter = ShowImageAdapte(this,items)
        val manager = LinearLayoutManager(this)
        rv_show_image.layoutManager = manager
        rv_show_image.adapter = mAdapter
    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showData(data: List<Picture>) {
       runOnUiThread {
           Log.d("main",data.size.toString())
           items.addAll(data)
           mAdapter.notifyDataSetChanged()
       }
    }



}
