package com.example.administrator.kotlin.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.administrator.kotlin.R
import com.example.administrator.kotlin.extensions.slideEnter
import com.example.administrator.kotlin.extensions.slideExit


/**
 * Created by Administrator on 2017/7/26 0026.
 * 标题栏管理
 */
interface ToorbarManager{
    val toolbar: Toolbar
    var toolbarTitle :String
        get() = toolbar.title.toString()
            set(value) {
                toolbar.title=value
            }
    fun initToolBar(){
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){

            }
        }
    }
    fun enableHomeAsUp(up:()->Unit){
        toolbar.navigationIcon=creatUpDrawble()
        toolbar.setNavigationOnClickListener { up() }

    }
    private fun creatUpDrawble()= with(DrawerArrowDrawable(toolbar.context)){
        progress=1f
        this
    }
    fun attachScroll (recyclerView: RecyclerView){
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy>0) toolbar.slideExit()else toolbar.slideEnter()
            }
        })
    }
}
