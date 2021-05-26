package com.everett.qiyu.adapter

interface OnItemClickListener {
    fun onClick(pos:Int,title:String,url:String){}
    fun onClick(pos: Int){}
    fun onClickLike(pos: Int){}
}