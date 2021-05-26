package com.everett.qiyu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.everett.qiyu.R
import com.everett.qiyu.bean.DataX

class ThirdFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var dataslist: MutableList<DataX>
    private lateinit var showlist: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var pages = 0
    private var lastVisibleItem = 0
    private lateinit var layoutManager: LinearLayoutManager

    override fun initView() {
        progressBar = vieww.findViewById(R.id.progressBar3)
        showlist = vieww.findViewById(R.id.ArticleRecycler)
        refreshLayout = vieww.findViewById(R.id.refreshList3)
        layoutManager = LinearLayoutManager(activity)
        showlist.layoutManager = layoutManager
        showlist.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        initRefreshLayout()
    }
    private fun initRefreshLayout() {
        refreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light, android.R.color.holo_red_light,
            android.R.color.holo_orange_light, android.R.color.holo_green_light
        )
        refreshLayout.setOnRefreshListener(this)

    }

    override fun getMyView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater!!.inflate(R.layout.third_activity, container, false)
    }

    override fun loadData() {


    }


    override fun onRefresh() {
        refreshLayout.isRefreshing = true
        //重新请求接口
        mHandler.postDelayed({
            refreshLayout.isRefreshing = false
            Toast.makeText(activity, "刷新成功", Toast.LENGTH_SHORT).show()
        }, 1000)

    }

}