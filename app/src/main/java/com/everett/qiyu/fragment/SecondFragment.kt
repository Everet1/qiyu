package com.everett.qiyu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.everett.qiyu.R



class SecondFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {



    private lateinit var showlist: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutManager: LinearLayoutManager
    private var pages = 0
    private var lastVisibleItem = 0
    private var refresh=false


    override fun initView() {
        showlist = vieww.findViewById(R.id.ObjectRecycler)
        progressBar = vieww.findViewById(R.id.progressBar2)
        refreshLayout = vieww.findViewById(R.id.refreshList2)

        initRefreshLayout()
        initRecycler()
    }

    private fun initRefreshLayout() {
        refreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_light, android.R.color.holo_red_light,
            android.R.color.holo_orange_light, android.R.color.holo_green_light
        )
        refreshLayout.setOnRefreshListener(this)

    }

    private fun initRecycler() {
        layoutManager = LinearLayoutManager(activity)
        showlist.layoutManager = layoutManager
        showlist.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun getMyView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater!!.inflate(R.layout.second_activity, container, false)
    }

    /**
     * 懒加载
     */
    override fun loadData() {

    }

    /**
     * 下滑刷新
     */
    override fun onRefresh() {
        refreshLayout.isRefreshing = true
    }
}