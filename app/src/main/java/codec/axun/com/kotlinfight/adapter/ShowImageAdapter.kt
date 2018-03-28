package codec.axun.com.kotlinfight.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        var params = imageHolder.iv_pic.layoutParams;
        params.width = 800
        params.height = 480
        Glide.with(context).load(items[position].url).override(800,480).into(imageHolder.iv_pic)
        imageHolder.tv_title.text = items[position].title
        holder.itemView.setOnClickListener {
            if (listener!=null){
                listener!!.onItemClick(holder.itemView,position)
            }
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }

   inner class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
       var iv_pic:ImageView
       var tv_title:TextView
       init {
           iv_pic = view.findViewById(R.id.show_image)
           tv_title = view.findViewById(R.id.tv_title)
       }

   }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }


    var listener:OnItemClickListener?=null

    interface OnItemClickListener{
        fun onItemClick(view: View,position: Int)
    }


}