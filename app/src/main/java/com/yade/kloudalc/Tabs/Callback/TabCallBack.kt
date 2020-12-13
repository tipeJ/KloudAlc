package com.yade.kloudalc.Tabs.Callback

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.yade.kloudalc.Tabs.PageManager

class TabCallBack : ItemTouchHelper.Callback {
    var adapter: ItemTouchHelperAdapter? = null

    constructor(adapter: ItemTouchHelperAdapter) : super() {
        this.adapter = adapter
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        var dragFlags = ItemTouchHelper.START or ItemTouchHelper.END
        var swipeFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        if (recyclerView?.layoutManager!!.width < recyclerView.layoutManager.height) {
            dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        }
        return if (viewHolder!!.adapterPosition == PageManager.instance.tablist.size) 0 else makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        if (target!!.adapterPosition != PageManager.instance.tablist.size) {
            adapter!!.onItemMove(viewHolder!!.adapterPosition, target.adapterPosition)
            return true
        }
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        adapter!!.onItemDismiss(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}