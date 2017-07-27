package com.example.administrator.kotlin.test

import android.util.Log
import java.net.URL

/**
 * Created by Administrator on 2017/7/21 0021.
 */
class Request(val url :String){

     public fun run(){
         Log.i(javaClass.simpleName, ": 你哈");
        val forecastJsonStr= URL(url).readText()
         Log.i(javaClass.simpleName, ": 你好");

         Log.i(javaClass.simpleName, forecastJsonStr)
         Log.i(javaClass.simpleName, ": 你好啊");

     }
}