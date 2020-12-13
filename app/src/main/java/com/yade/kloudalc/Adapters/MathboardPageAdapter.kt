package com.yade.kloudalc.Adapters

import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yade.kloudalc.Keyboard.Constants.ConsFragment
import com.yade.kloudalc.Keyboard.Fragments.FuncFragment
import com.yade.kloudalc.Keyboard.Fragments.NumFragment
import com.yade.kloudalc.Keyboard.Fragments.VarFragment
import com.yade.kloudalc.Keyboard.Mathboard
import com.yade.kloudalc.Keyboard.Mathboard.Companion.typeFun
import com.yade.kloudalc.Keyboard.Mathboard.Companion.typeMulti
import com.yade.kloudalc.KloudalcApp
import com.yade.kloudalc.Utils.LicenseUtils


class MathboardPageAdapter : FragmentPagerAdapter {

    //Default mode is multi-mode, which includes NumFragment
    var type = typeMulti

    constructor(fm: FragmentManager?, type: String) : super(fm) {
        this.type = type
    }

    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment {
        if (LicenseUtils.hasplus()) {
            return when (type) {
                typeFun -> when (position) {
                    0 -> ConsFragment()
                    1 -> VarFragment()
                    else -> FuncFragment()
                }
                else -> when (position) {
                    0 -> ConsFragment()
                    1 -> VarFragment()
                    2 -> NumFragment()
                    else -> FuncFragment()
                }
            }
        } else {
            return when (type) {
                typeFun -> when (position) {
                    0 -> ConsFragment()
                    else -> FuncFragment()
                }
                else -> when (position) {
                    0 -> ConsFragment()
                    1 -> NumFragment()
                    else -> FuncFragment()
                }
            }
        }
    }

    override fun getCount(): Int {
        if (LicenseUtils.hasplus()) {
            return when (type) {
                typeFun -> 3
                else -> 4
            }
        } else {
            return when (type) {
                typeFun -> 3
                else -> 4
            } - 1
        }

    }
}