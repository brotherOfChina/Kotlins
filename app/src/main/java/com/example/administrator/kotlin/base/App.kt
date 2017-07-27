package com.example.administrator.kotlin.base

import android.app.Application
import com.example.administrator.kotlin.extensions.DelegatesExt

/**
 * Created by Administrator on 2017/7/24 0024.
 *
 */
class App : Application() {
    companion object {
         var instance :App by DelegatesExt.notNullSingleValue()
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}