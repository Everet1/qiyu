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

class FourthFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var progressbar4: ProgressBar



    private val tag1 = "FourthFragment"


    override fun initView() {
        recyclerView = vieww.findViewById(R.id.tressRecycler)
        refreshLayout = vieww.findViewById(R.id.refreshList4)
        progressbar4 = vieww.findViewById(R.id.progressBar4)
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
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

    override fun getMyView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater!!.inflate(R.layout.fourth_activity, container, false)
    }

    override fun loadData() {

    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }

}