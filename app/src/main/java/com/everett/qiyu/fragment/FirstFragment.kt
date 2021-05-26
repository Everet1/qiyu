package com.everett.qiyu.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.everett.qiyu.R
import com.everett.qiyu.bean.JsonData
import com.everett.qiyu.constant.Constant
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.first_activity.*
import okhttp3.Request

class FirstFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recyclershow: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout

    private lateinit var layoutManager: LinearLayoutManager


    /**
     * 初始化页面布局
     */
    override fun initView() {
        recyclershow = vieww.findViewById(R.id.homerecyclerview) as RecyclerView
        refreshLayout = vieww.findViewById(R.id.homeswipeRefresh) as SwipeRefreshLayout
        layoutManager = LinearLayoutManager(activity)
        recyclershow.layoutManager = layoutManager
        recyclershow.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        initRefreshLayout()
    }

    private fun initRefreshLayout() {
        refreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light, android.R.color.holo_red_light,
            android.R.color.holo_orange_light, android.R.color.holo_green_light
        )
        refreshLayout.setOnRefreshListener(this)

    }

    /**
     * 引入布局
     */
    override fun getMyView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater!!.inflate(R.layout.first_activity, container, false)
    }

    /**
     * 延迟加载的数据
     */
    override fun loadData() {
        homeprogressBar.visibility=View.VISIBLE


    }


    private fun requestNetwork(refresh:Boolean) {
        val observable = Observable.create(ObservableOnSubscribe<JsonData> { emitter ->
            val request = Request.Builder()
                .url("")
                .build()
            val respones = Constant.client.newCall(request).execute()
            val responseData = respones.body()?.string()


            emitter?.onComplete()
        }).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())//观察者运行在主线程


        //观察者
        val observer = object : Observer<JsonData> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: JsonData) {

            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable?) {
                Log.e("onError", "************")
            }
        }
        observable.subscribe(observer)
    }


    //下滑刷新
    override fun onRefresh() {
        refreshLayout.isRefreshing = true
        requestNetwork(true)//请求接口获取数据

    }


}