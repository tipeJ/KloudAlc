package com.yade.kloudalc.Tabs

import com.yade.kloudalc.Keyboard.ActionButton

class CalcPage : Page{
    var id2 : Long?= null
    var type2 : String?= null

    override var type = PageManager.kCalcPage

    var input = ArrayList<ActionButton>()

    override var id: Long
        get() = id2 ?: 0
        set(value) {id2 = value}
}