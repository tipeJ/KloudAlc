package com.yade.kloudalc.Keyboard

/**
 * Created by Tiitus on 30.12.2017.
 */
class SpecButton : OperButton {
    constructor(Title: String?) : super(Title)
    constructor(Title: String?, Inside: String?) : super(Title, Inside)
    constructor(Title: String?, subButton: ActionButton) : super(Title, subButton)
    constructor(Title: String?, subButtons: ArrayList<ActionButton>) : super(Title, subButtons)
    constructor(Title: String?, Inside: String?, subButtons: ArrayList<ActionButton>) : super(Title, Inside, subButtons)
    constructor(Title: String?, Inside: String?, subButton: ActionButton) : super(Title, Inside, subButton)
}