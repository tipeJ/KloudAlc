package com.yade.kloudalc.Views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.FuncButton

/**
 * Created by Tiitus on 28.12.2017.
 */
class MathButtonView : Button {
    var but : ActionButton ? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setButton(button: ActionButton){
        setText(button.getTitle())
        but = button
    }
}