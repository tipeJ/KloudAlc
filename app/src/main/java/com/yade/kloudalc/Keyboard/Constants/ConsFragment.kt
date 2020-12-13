package com.yade.kloudalc.Keyboard.Constants

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import com.yade.kloudalc.Adapters.MathButtonsAdapter
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.ButtonManager
import com.yade.kloudalc.R
import kotlinx.android.synthetic.main.mathboard_fragment_num.view.*


class ConsFragment : Fragment, RecyclerViewExpandableItemManager.OnGroupCollapseListener, RecyclerViewExpandableItemManager.OnGroupExpandListener {

    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mathboard_fragment_num,container,false)

        val manager = RecyclerViewExpandableItemManager(savedInstanceState)

        val adapter = ConstantsAdapter(activity as Kactivity)

        val animator = RefactoredDefaultItemAnimator()
        animator.supportsChangeAnimations = false


        val scroller = RecyclerView(context)
        scroller.layoutManager = LinearLayoutManager(context)
        scroller.adapter = manager.createWrappedAdapter(adapter)
        scroller.itemAnimator = animator
        scroller.setHasFixedSize(false)

        manager.attachRecyclerView(scroller)

        v.mathboard_num.addView(scroller, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        return v
    }

    override fun onGroupCollapse(groupPosition: Int, fromUser: Boolean, payload: Any?) {
    }

    override fun onGroupExpand(groupPosition: Int, fromUser: Boolean, payload: Any?) {
        if (fromUser){
            adjustScrollPosition(groupPosition)
        }
    }
    private fun adjustScrollPosition(groupPosition: Int){
    }
}