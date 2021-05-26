package com.everett.qiyu.constant


import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import com.everett.qiyu.retrofit.RetrofitService
import okhttp3.OkHttpClient

object Constant {
    const val CONTENT_URL_KEY = "url"
    const val CONTENT_TITLE_KEY = "title"
    const val CONTENT_ID_KEY = "id"
    const val CONTENT_BODY_KEY = "body"
    const val BASE_URL = "https://www.wanandroid.com"

    lateinit var retrofithelper: RetrofitService

    var isNetWrokable: Boolean = true
    var isload: Boolean = false

    val client = OkHttpClient()

    var oldcookie = ""

    fun getchenjin(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE )
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.statusBarColor = Color.TRANSPARENT
    }



}