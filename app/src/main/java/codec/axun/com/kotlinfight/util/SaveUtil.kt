package codec.axun.com.kotlinfight.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

object SaveUtil {
    fun saveBitmap(context: Context,bitmap: Bitmap?,name: String):String{
        val path = Environment.getExternalStorageDirectory().absolutePath+File.separator+"images"
        return saveBitmap(context,bitmap,path,name)
    }

    fun saveBitmap(context: Context,bitmap: Bitmap?,path:String,name:String): String{
        val pFile = File(path)
        val file:File = File(path+File.separator+name)
        var text:String
       if (!pFile.exists()){
           pFile.mkdirs()
       }
        if (!file.exists()){
            file.createNewFile()
            text = "图片保存到"+file.absolutePath
        }else{
            text = "此图片已保存"
        }
        val fos = FileOutputStream(file)
        bitmap!!.compress(Bitmap.CompressFormat.JPEG,100,fos)
        fos.flush()
        fos.close()
        return text
    }
}