package com.yade.kloudalc.Tabs

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Fragments.CalcFragment
import com.yade.kloudalc.R
import com.yade.kloudalc.Tabs.Callback.ItemTouchHelperAdapter
import kotlinx.android.synthetic.main.tab.view.*

class TabAdapter : RecyclerView.Adapter<TabAdapter.TabHolder>, ItemTouchHelperAdapter {
    var par: Kactivity? = null

    constructor() : super()

    class TabHolder : RecyclerView.ViewHolder {
        var text: TextView? = null
        var separator: View? = null

        constructor(itemView: View?) : super(itemView) {
            text = itemView?.findViewById(R.id.tab_index)
            separator = itemView?.tab_separator
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.tab, parent, false)
        return TabHolder(view)
    }

    override fun getItemCount(): Int {
        return PageManager.instance.tablist.size + 1
    }

    override fun onBindViewHolder(holder: TabHolder, position: Int) {
        if (position == PageManager.instance.tablist.size) {
            holder?.separator?.visibility = View.INVISIBLE
            holder?.text?.text = "+"
            holder?.itemView?.setOnClickListener { view ->
                val index = PageManager.instance.newPage(PageManager.kCalcPage)
                notifyItemInserted(PageManager.instance.tablist.size - 1)
                par?.switchPages(PageManager.instance.tablist.indexOf(PageManager.instance.findPage(index)))
            }
        } else {
            holder?.text?.text = PageManager.instance.tablist[position].id.toString()
            holder?.itemView!!.setOnClickListener { view ->
                par?.switchPages(position)
            }
        }
        holder?.itemView?.isClickable = true
    }

    // Needed for smooth animations if we want to keep using notifyDataSetChanged()
    class lmanager : LinearLayoutManager{
        constructor(context: Context?) : super(context)
        constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

        override fun supportsPredictiveItemAnimations(): Boolean {
            return true
        }
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition == PageManager.instance.tablist.size) return

        PageManager.instance.movePage(fromPosition, toPosition)

        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        if (position != PageManager.instance.tablist.size){ return PageManager.instance.tablist[position].id} else {return -1}
    }

    override fun onItemDismiss(position: Int) {
        if (position == PageManager.instance.tablist.size) {
            return
        } else if (PageManager.instance.tablist.size == 1) {
            PageManager.instance.newPage(CalcFragment.longKey)
            notifyItemInserted(position + 1)
            par?.switchPages(1)
        } else if (position == PageManager.instance.currentPageIndex()) {
            if (position == 0){
                par?.switchPages(1)
            } else {
                par?.switchPages(position - 1)
            }
        }
        PageManager.instance.removePage(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position,itemCount)
//        notifyDataSetChanged()
    }
}