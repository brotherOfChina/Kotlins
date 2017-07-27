package com.example.administrator.kotlin.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by Administrator on 2017/7/26 0026.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)