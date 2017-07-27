package com.example.administrator.kotlin.test

import android.util.Log
import com.example.administrator.kotlin.test.Animal

/**
 * Created by Administrator on 2017/7/21 0021.
 */
open class Person(name:String,gender:String): Animal(name){
    init {
        Log.i("zpj", "性别: "+name+gender)

    }

    fun getInfo(name:String,age:Int): String =
         "name"+name+"age"+age


}