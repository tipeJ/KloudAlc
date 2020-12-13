package com.yade.kloudalc.Keyboard

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.yade.kloudalc.Adapters.MathButtonsAdapter
import com.yade.kloudalc.Adapters.MathboardPageAdapter
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.R
import com.yade.kloudalc.Tabs.PageManager
import com.yade.kloudalc.Utils.LicenseUtils
import com.yade.kloudalc.Views.MathInput
import kotlinx.android.synthetic.main.mathboard.*
import kotlinx.android.synthetic.main.mathboard.view.*

class Mathboard : Fragment, View.OnTouchListener {
    val TAG = "Mathboard"
    var popupWindow: PopupWindow? = null


    constructor() : super()

    companion object {
        val typeMulti = "MathboardPageAdapter.Multi"
        val typeFun = "MathboardPageAdapter.Fun"

        val typeKey = "Mathboard.Tyoekey"

        fun newInstance(type: String): Mathboard {
            val args = Bundle()
            args.putString(typeKey, type)

            val board = Mathboard()
            board.arguments = args

            return board
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mathboard, container, false)

        val callBack = (activity as Kactivity).currentFragment

        val type = arguments?.getString(typeKey)

        val pagerAdapter = if (type == null) MathboardPageAdapter(childFragmentManager) else MathboardPageAdapter(childFragmentManager, type)

        v.mathboard_viewpager.adapter = pagerAdapter
        v.mathboard_viewpager.currentItem = if (LicenseUtils.hasplus()) 2 else 1
        v.mathboard_indicator.count = pagerAdapter.count
        v.mathboard_indicator.unselectedColor = R.color.colorPrimaryDark
        v.mathboard_indicator.selection = if (LicenseUtils.hasplus()) 2 else 1
        v.mathboard_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mathboard_indicator.selection = position
            }

            override fun onPageSelected(position: Int) {
            }
        })

        val row = ButtonRow(context)
        row.orientation = LinearLayout.HORIZONTAL
        row.callBack = callBack
        row.setButtons(ButtonManager.getTopRowButtons().size)
        row.setButtonData(ButtonManager.getTopRowButtons())
        v.mathboard_topbar.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
        v.mathboard_topbar.addView(row, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        return v
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        if (popupWindow != p0 && p1?.action == MotionEvent.ACTION_DOWN) {
            popupWindow?.dismiss()
            return true
        }
        return false
    }
}