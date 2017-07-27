package com.example.administrator.kotlin.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * Created by Administrator on 2017/7/26 0026.
 * view 的工具类
 */
val View.ctx:Context
    get() = context
var TextView.textColor:Int
    get() = currentTextColor
    set(v)=setTextColor(v)
fun View.slideExit(){
    if (translationY==0f)
        animate().translationY(-height.toFloat())
}
fun View.slideEnter(){
    if (translationY<0f)
        animate().translationY(0f)
}