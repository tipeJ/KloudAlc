package com.yade.kloudalc.Utils

import android.content.res.Configuration
import android.content.res.Resources

object DisplayUtils {
    val sizeSmall = 0
    val sizeNormal = 1
    val sizeLarge = 2

    fun getScreenWidth(): Int{
        return Resources.getSystem().displayMetrics.widthPixels
    }
    fun getScreenHeight(): Int{
        return Resources.getSystem().displayMetrics.heightPixels
    }
    fun isLarge() : Boolean{
        return when(Resources.getSystem().configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK){
               Configuration.SCREENLAYOUT_SIZE_SMALL or Configuration.SCREENLAYOUT_SIZE_NORMAL -> false
            else -> true
        }
    }
}