package codec.axun.com.kotlinfight.adapter

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import codec.axun.com.kotlinfight.R
import codec.axun.com.kotlinfight.bean.Picture
import com.bumptech.glide.Glide

/**
 * Created by ckz on 2018/3/27.
 */
class ShowImageAdapte(private val context: Context, private val items:List<Picture>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_show_image,parent,false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val imageHolder = holder as ImageHolder
        Glide.with(context).load(items.get(position).url).into(imageHolder.iv_pic)
    }

    override fun getItemCount(): Int {
       return if (items==null) 0 else items.size
    }

   inner class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
       var iv_pic:ImageView

       init {
           iv_pic = view.findViewById(R.id.show_image)
       }

   }
}