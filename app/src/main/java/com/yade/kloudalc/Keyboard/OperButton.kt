package com.yade.kloudalc.Keyboard


open class OperButton : ActionButton{

    var Name : String? = null
    var value : String? = null
    var acName : String? = null
    var subs : ArrayList<ActionButton> = ArrayList()
    override
    var id : Long
        get() = id
    set(value) {id = value}

    constructor(Title: String?){
        Name = Title
        value = Title
    }
    constructor(Title: String?, Inside: String?){
        Name = Title
        value = Inside
    }
    constructor(Title: String?, subButton: ActionButton){
        this.Name = Title
        value = Title
        subs.add(subButton)
    }
    constructor(Title: String?, subButtons: ArrayList<ActionButton>){
        this.Name = Title
        value = Title
        subs = subButtons
    }
    constructor(Title: String?, Inside : String?, subButtons: ArrayList<ActionButton>){
        this.Name = Title
        value = Inside
        subs = subButtons
    }
    constructor(Title: String?, Inside : String?, subButton: ActionButton){
        this.Name = Title
        value = Inside
        subs.add(subButton)
    }

    constructor(Name: String?, value: String?, acName: String?, subs: ArrayList<ActionButton>) {
        this.Name = Name
        this.value = value
        this.acName = acName
        this.subs = subs
    }

    constructor(Name: String?, value: String?, acName: String?) {
        this.Name = Name
        this.value = value
        this.acName = acName
    }


    override fun getTitle(): String? {
        return Name
    }

    override fun getInside(): String? {
        return value
    }

    override fun getSubButtons(): ArrayList<ActionButton> {
        return subs
    }

    override fun getACTitle(): String? {
        acName?.let { return acName }
        return Name
    }
}