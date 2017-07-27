package com.example.administrator.kotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.administrator.kotlin.R
import com.example.administrator.kotlin.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    companion object {
        val ZIP_CODE="zipCode"
        val DEFAULT_ZIP=94043L
    }
    var zipCode:Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        cityCode.setText(zipCode.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?)=when(item?.itemId){
        android.R.id.home->{onBackPressed();true}
        else ->false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode=cityCode.text.toString().toLong()
    }

}
