package codec.axun.com.kotlinfight.activity

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import codec.axun.com.kotlinfight.R
import codec.axun.com.kotlinfight.bean.Picture
import codec.axun.com.kotlinfight.presenter.IShowBigPresenter
import codec.axun.com.kotlinfight.view.IShowBigView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_show_big_image.*
import uk.co.senab.photoview.PhotoViewAttacher
import java.lang.Exception

class ShowBigImageActivity : AppCompatActivity(),IShowBigView{

    private lateinit var name:String
    private lateinit var presenter:IShowBigPresenter
    private lateinit var attacher: PhotoViewAttacher
    private var type = 1
    private var bitmap:Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_show_big_image)
        presenter = IShowBigPresenter(this,this)
        presenter.showUI()
        initClick()
    }

    override fun showData(data: Picture) {
        val url:String = data.url!!.substring(0,data.url!!.lastIndexOf("_"))+"_1920x1080.jpg"
        name = data.url!!.substring(data.url!!.lastIndexOf("/")+1)
       Log.d("ShowBig",url+"\n"+data.url)
        Glide.with(this).load(url).asBitmap().listener(object : RequestListener< String, Bitmap> {
            override fun onException(p0: Exception?, p1: String?, p2: Target<Bitmap>?, p3: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(p0: Bitmap?, p1: String?, p2: Target<Bitmap>?, p3: Boolean, p4: Boolean): Boolean {

                attacher = PhotoViewAttacher(show_image)
                attacher.setOnPhotoTapListener(object : PhotoViewAttacher.OnPhotoTapListener {
                    override fun onPhotoTap(p0: View?, p1: Float, p2: Float) {
                        presenter.clickPic(type)
                    }
                })
                return false
            }
        }).into(object:SimpleTarget<Bitmap>(){
            override fun onResourceReady(p0: Bitmap?, p1: GlideAnimation<in Bitmap>?) {
                if (p0!=null){
                    bitmap = p0!!
                }
                show_image.setImageBitmap(p0)
            }
        })
        tv_title.text=data.title
    }

    override fun showImageTitle() {
        type=0
        tv_title.visibility = View.VISIBLE
        val tAni : ObjectAnimator = ObjectAnimator.ofFloat(tv_title,"translationY",1000f,0f)
        val aAni : ObjectAnimator = ObjectAnimator.ofFloat(iv_save,"alpha",1f,0f)
        val set : AnimatorSet = AnimatorSet()
        set.playTogether(tAni,aAni)
        set.duration = 500
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                iv_save.visibility = View.GONE
            }
        })
    }

    override fun showSave() {
        type=1
        iv_save.visibility = View.VISIBLE
        val tAni : ObjectAnimator = ObjectAnimator.ofFloat(tv_title,"translationY",0f,1000f)
        val aAni : ObjectAnimator = ObjectAnimator.ofFloat(iv_save,"alpha",0f,1f)
        val set : AnimatorSet = AnimatorSet()
        set.playTogether(tAni,aAni)
        set.duration = 500
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                tv_title.visibility = View.GONE
            }
        })
    }

    private fun initClick(){
        btn_back.setOnClickListener{
            finish()
        }

        iv_save.setOnClickListener {
            request()
        }
    }

    private val permissions :Array<String> = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var ints  = arrayListOf<Int>()
    private fun request(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (permission in permissions){
                if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                    ints.add(0)
                }else{
                    ints.add(1)
                }
            }
            if (ints.contains(0)){
                requestPermissions(permissions,100)
            }else{
                saveBitmap()
            }
        }else{
            saveBitmap()
        }
    }

    private fun saveBitmap(){
        val toast = presenter.downloadPic(this,bitmap,name)
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.size>0){
            val per = arrayListOf<Int>()
            for (result in grantResults){
                if (result == PackageManager.PERMISSION_GRANTED){
                    per.add(1)
                }else{
                    per.add(0)
                }
            }
            if (!per.contains(0)){
                saveBitmap()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bitmap!=null){
            bitmap!!.recycle()
        }
    }

}
