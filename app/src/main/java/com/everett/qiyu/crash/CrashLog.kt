package com.everett.qiyu.crash

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import com.everett.qiyu.constant.LogUtil
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class CrashLog : Thread.UncaughtExceptionHandler {
    private val TAG = "CrashHandler"
    private val DEBUG = true
    private val PATH = Environment.getExternalStorageDirectory().absolutePath+"/CrashTest/log/";
    private val FILE_NAME = "crash";
    private val FILE_NAME_SUFFIX = ".log";
    private var mDefaultCrashHandler: Thread.UncaughtExceptionHandler? = null
    private var mContext: Context? = null

    fun getsInstance(): CrashLog {
        return this@CrashLog
    }

    fun initv(context: Context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
        mContext = context
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
        //dumpExceptionToSDCard(e)
        if (mDefaultCrashHandler != null)
            mDefaultCrashHandler?.uncaughtException(t, e)
        else {
            Process.killProcess(Process.myPid())
        }
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun dumpExceptionToSDCard(ex: Throwable) {
        val dir = File(PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val current = System.currentTimeMillis()
        val time: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(current))
        val file = File(
            PATH + FILE_NAME + time.replace(" ", "_").replace(
                ":".toRegex(),
                "-"
            ) + FILE_NAME_SUFFIX
        )
        try {
            val pw = PrintWriter(BufferedWriter(FileWriter(file)))
            pw.println(time)
            dumpPhoneInfo(pw)
            pw.println()
            ex.printStackTrace(pw)
            pw.close()
        } catch (e: Exception) {
            LogUtil.e(TAG, "dump crash info failed")
        }
    }


    @Throws(PackageManager.NameNotFoundException::class)
    private fun dumpPhoneInfo(pw: PrintWriter) {
        val pm = mContext!!.packageManager
        val pi =
            pm.getPackageInfo(mContext!!.packageName, PackageManager.GET_ACTIVITIES)
        pw.print("App Version: ")
        pw.print(pi.versionName)
        pw.print("_")
        pw.println(pi.versionCode)
        //Android版本号
        pw.print("OS Version: ")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)
        //手机制造商
        pw.print("Vendor: ")
        pw.println(Build.MANUFACTURER)
        //手机型号
        pw.print("Model: ")
        pw.println(Build.MODEL)
        //CPU架构
        pw.print("CPU ABI: ")
        pw.println(Build.CPU_ABI)
    }


}