package com.yade.kloudalc.Keyboard

/**
 * Created by tiitu on 28.2.2018.
 */
class Constant : ActionButton{
    override var id: Long = 0

    var Name : String? = null
    var value : String? = null
    var acName : String? = null

    var description: String ?= null

    constructor(Name: String?, value: String?, acName: String?,description: String) {
        this.Name = Name
        this.value = value
        this.acName = acName
        this.description = description
    }
    constructor(Title: String?, Inside: String?,description: String){
        Name = Title
        value = Inside
        this.description = description
    }
    constructor(Title: String?,description: String){
        Name = Title
        this.description = description
    }


    override fun getTitle(): String? {
        return Name
    }

    override fun getACTitle(): String? {
        acName?.let { return acName }
        return Name
    }

    override fun getInside(): String? {
        value?.let { return value }
        return Name
    }

    override fun getSubButtons(): ArrayList<ActionButton> {
        return ArrayList()
    }

}