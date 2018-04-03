package codec.axun.com.kotlinfight.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import codec.axun.com.kotlinfight.R
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.presenter.IShowBigPresenter
import codec.axun.com.kotlinfight.view.IShowBigView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_show_big_image.*
import uk.co.senab.photoview.PhotoViewAttacher
import java.lang.Exception

class ShowBigImageActivity : AppCompatActivity(),IShowBigView{


    private lateinit var presenter:IShowBigPresenter
    private lateinit var attacher: PhotoViewAttacher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_show_big_image)
        presenter = IShowBigPresenter(this,this)
        presenter.showUI()
    }

    override fun showData(data: Picture) {
        Glide.with(this).load(data.url).listener(object : RequestListener<String, GlideDrawable> {
            override fun onException(p0: Exception?, p1: String?, p2: Target<GlideDrawable>?, p3: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(p0: GlideDrawable?, p1: String?, p2: Target<GlideDrawable>?, p3: Boolean, p4: Boolean): Boolean {
                attacher = PhotoViewAttacher(show_image)
                return false
            }

        }).into(show_image)
    }

    override fun showImageTitle() {

    }

    override fun showSave() {

    }

}
