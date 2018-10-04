package com.tinkoff.androidcourse


interface ItemTouchHelperAdapter {
    fun onItemMove(fromPos: Int, toPos: Int)
    fun onItemDismiss(pos: Int)
}