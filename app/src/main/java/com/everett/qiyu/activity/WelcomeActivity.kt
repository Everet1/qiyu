package com.everett.qiyu.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.everett.qiyu.MainActivity
import com.everett.qiyu.R
import com.everett.qiyu.constant.Constant
import kotlinx.android.synthetic.main.welcome_activity.*


class WelcomeActivity : AppCompatActivity() {
    private var timescount = 5
    lateinit var timebtn: Button

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            timescount--
            timebtn.text = "$timescount 跳过"
            if (timescount > 0) {
                val message: Message = this.obtainMessage(1)
                this.sendMessageDelayed(message, 1000)
            } else {
                goHome()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        timebtn = timeBtn1
        timebtn.setOnClickListener {
            handler.removeMessages(1)//移除消息
            goHome()
        }
        val message = handler.obtainMessage(1)
        handler.sendMessageDelayed(message, 1000)
    }

    fun goHome() {
        if (Constant.isload) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}
