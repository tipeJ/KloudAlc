package com.yade.kloudalc.Tabs.Callback

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)

}