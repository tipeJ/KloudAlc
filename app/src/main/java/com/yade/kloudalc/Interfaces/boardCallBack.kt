package com.yade.kloudalc.Interfaces

import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.Mathboard
import com.yade.kloudalc.Tabs.Page

/**
 * Created by Tiitus on 10.1.2018.
 */
interface boardCallBack {
    val board : Mathboard

    fun buttonClicked(button: ActionButton)
    fun buttonRemoved(position: Int)
    fun move(amount: Int, direction: Int)
    fun loadPage(page: Page)
}