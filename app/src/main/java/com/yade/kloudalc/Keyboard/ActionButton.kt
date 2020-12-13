package com.yade.kloudalc.Keyboard


interface ActionButton {
    var id : Long

    fun getTitle() : String?

    fun getACTitle() : String?

    fun getInside() : String?

    fun getSubButtons() : ArrayList<ActionButton>

}