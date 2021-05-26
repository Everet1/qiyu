package com.everett.qiyu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everett.qiyu.R
import com.everett.qiyu.bean.Bannerlist
import com.everett.qiyu.bean.DataX
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class HomeListAdapter(private val list: MutableList<DataX>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val headtype = 1
    private val roottype = 2
    private val tipstape = 3
    private var hasMore = true
    private var firstload = true
    private lateinit var listbanner: List<Bannerlist>

    private lateinit var context: Context
    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class HeadHodler(v: View) : RecyclerView.ViewHolder(v) {
        //获取头view中的组件
    }

    class RootHodler(v: View) : RecyclerView.ViewHolder(v) {
        //就是获取id
    }

    class TipsHodler(v: View) : RecyclerView.ViewHolder(v) {
        //尾布局id获取
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            headtype -> HeadHodler(
                //获取头view
                LayoutInflater.from(context).inflate(R.layout.banner_view, parent, false)
            )
            tipstape -> TipsHodler(
                //获取尾节点布局
                LayoutInflater.from(context).inflate(R.layout.banner_view, parent, false)
            )
            else -> RootHodler(
                //
                LayoutInflater.from(context).inflate(R.layout.banner_view, parent, false)
            )
        }
    }

    override fun getItemCount(): Int = list.size + 2 //
    override fun getItemViewType(position: Int): Int {

        return when (position) {
            0 -> headtype
            itemCount - 1 -> tipstape
            else -> roottype
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeadHodler) {
        } else if (holder is RootHodler) {
            if (position < list.size + 2) {

            } else if (holder is TipsHodler) {

            }
        }
    }

    private fun initBanner(banner1: Banner) {
        val titlelist = ArrayList<String>()
        val imagelist = ArrayList<String>()
        listbanner.forEach {
            titlelist.add(it.title)
            imagelist.add(it.imagePath)
        }
        banner1.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        banner1.setBannerTitles(titlelist)
        banner1.setImages(imagelist)
        banner1.setImageLoader(MyLoader())
        banner1.setBannerAnimation(Transformer.Default)
        banner1.setOnBannerListener {
            //binner点击事件
        }
        banner1.setDelayTime(5000)
        banner1.isAutoPlay(true)
        banner1.setIndicatorGravity(BannerConfig.CENTER)
        banner1.start()
    }

    /**
     * 加载图片
     */
    class MyLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
            Glide.with(context).load(path).into(imageView)
        }
    }


}