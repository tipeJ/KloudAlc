package com.yade.kloudalc.Tabs

class ConversionPage : Page {
    var id2 : Long?= null

    override var type = PageManager.kConvPage

    override var id: Long
        get() = id2 ?: 0
        set(value) {id2 = value}
}