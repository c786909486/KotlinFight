package codec.axun.com.kotlinfight.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import codec.axun.com.kotlinfight.R
import codec.axun.com.kotlinfight.adapter.ShowImageAdapte
import codec.axun.com.kotlinfight.bean.KeyCenter
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.presenter.IMainPresenter
import codec.axun.com.kotlinfight.view.IMainView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView {
    private var page = 1;
    private var items:ArrayList<Picture> = arrayListOf()
    private var mAdapter:ShowImageAdapte = ShowImageAdapte(this,items)
    private lateinit var presenter:IMainPresenter
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initRefresh()
        presenter = IMainPresenter(this)
        presenter.getNetData(KeyCenter.url+page)
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT)
        toast.duration = Toast.LENGTH_SHORT

    }

    private fun initRecyclerView(){
        val manager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        rv_show_image.layoutManager = manager
        rv_show_image.itemAnimator = DefaultItemAnimator()
        rv_show_image.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                manager.invalidateSpanAssignments()
            }
        })
        rv_show_image.adapter = mAdapter
        mAdapter.setOnItemClickListener(object :ShowImageAdapte.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
//                toast.setText(items[position].title)
//                toast.show()
                val intent = Intent(this@MainActivity,ShowBigImageActivity::class.java)
                intent.putExtra("data",items[position])
                startActivity(intent)
            }

        })
    }

    private fun initRefresh(){
        refresh_layout.setEnableRefresh(false)
        refresh_layout.setEnableLoadmore(true)
        refresh_layout.setOnRefreshListener(object:RefreshListenerAdapter(){
            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                presenter.getNetData(KeyCenter.url+page)
            }
        })

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
           page++
           refresh_layout.finishLoadmore()
       }
    }



}


