package com.yade.kloudalc.Keyboard.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.yade.kloudalc.Fragments.ConversionFragment
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.*
import com.yade.kloudalc.R
import kotlinx.android.synthetic.main.mathboard_fragment_num.view.*


class NumFragment : Fragment {
    var callback : boardCallBack?= null

    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mathboard_fragment_num,container,false)


        callback = (activity as Kactivity).currentFragment
        ButtonManager.getMainButtons().forEach { buttongroup ->
            val row = ButtonRow(context)
            row.orientation = LinearLayout.VERTICAL
            row.callBack = callback

            //IF fragment (callback) is conversionfragment then subtract "=" from the list
            val group = if (buttongroup.contains(ButtonManager.equalsButton) && callback is ConversionFragment) buttongroup.subList(0,buttongroup.size-1) else buttongroup

            row.setButtons(group.size)
            row.setButtonData(ArrayList(group))
            v.mathboard_num.addView(row, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f))
        }

        return v
    }
}