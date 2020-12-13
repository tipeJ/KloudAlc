package com.yade.kloudalc.Keyboard.Fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.common.collect.Lists
import com.yade.kloudalc.Adapters.MathButtonsAdapter
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.ButtonManager
import com.yade.kloudalc.KloudalcApp
import com.yade.kloudalc.R
import com.yade.kloudalc.Utils.DisplayUtils
import kotlinx.android.synthetic.main.mathboard_fragment_num.*
import kotlinx.android.synthetic.main.mathboard_fragment_num.view.*

/**
 * Created by Tiitus on 12.1.2018.
 */
class FuncFragment : Fragment {
    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mathboard_fragment_num, container, false)

        var buttons = ButtonManager.getFunButtons()
        val newButtons = ArrayList<ArrayList<ActionButton>>()
        if (PreferenceManager.getDefaultSharedPreferences(KloudalcApp.instance).getBoolean("functions_enable_rowlength", false)) {
            //Default row length is 5
            val rowlength = PreferenceManager.getDefaultSharedPreferences(KloudalcApp.instance).getString("functions_rowlength", "5").toInt()
            val smlrlist = ArrayList(Lists.partition(ArrayList(ButtonManager.getAllFunButtons()), rowlength))
            smlrlist.forEach { btns ->
                newButtons.add(ArrayList(btns))
            }
            buttons = newButtons
        } else {
            if (!DisplayUtils.isLarge()) {
                ButtonManager.getFunButtons().forEach { row ->
                    if (row.size > 3) {
                        val smallerlist = Lists.partition(row, 3)
                        smallerlist.forEach { slist ->
                            newButtons.add(ArrayList(slist))
                        }
                    } else {
                        newButtons.add(row)
                    }
                }
                buttons = newButtons
            }
        }
        val adapter = MathButtonsAdapter(context!!, buttons)
        adapter.callBack = (activity as Kactivity).currentFragment

        val scroller = RecyclerView(context)
        scroller.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        scroller.adapter = adapter

        v.mathboard_num.addView(scroller, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        return v
    }
}