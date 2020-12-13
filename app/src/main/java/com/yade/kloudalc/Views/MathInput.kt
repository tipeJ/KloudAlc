package com.yade.kloudalc.Views

import android.content.Context
import android.os.Build
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.ButtonManager
import com.yade.kloudalc.Keyboard.NumButton
import com.yade.kloudalc.Keyboard.SpecButton
import com.yade.kloudalc.Tabs.CalcPage
import com.yade.kloudalc.Tabs.PageManager
import com.yade.kloudalc.Utils.ParseUtils
import kotlinx.android.synthetic.main.fragment_calculator.view.*


class MathInput : TextView {
    var actual = ArrayList<ActionButton>()

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
//        if (Build.VERSION.SDK_INT >= 11) {
//            setRawInputType(InputType.TYPE_CLASS_TEXT)
//            setTextIsSelectable(true)
//        } else {
//            setRawInputType(InputType.TYPE_NULL)
//            setFocusable(true)
//        }
//        setTextIsSelectable(true)
        setSingleLine()
        movementMethod = ScrollingMovementMethod()

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun addAction(button: ActionButton){
        //Please forgive me. This is meant to prevent operators found in the ButtonManager class to be added successively and I tried to fit the whole damn thing in a single row
        if (ButtonManager.operators.contains(button.getTitle()) && ((!actual.isEmpty() && ButtonManager.operators.contains(actual.last().getTitle())) || (actual.size == 0 && button.getTitle() != "-"))) return

        when (button) {
            is SpecButton ->
                when (button.Name) {
                    "DEL" -> removeAction()
                    "CLR" -> clear()
                }
            else ->
                actual.add(button)
        }
        (PageManager.instance.currentPage as CalcPage).input.clear()
        actual?.forEach { btn ->
            (PageManager.instance.currentPage as CalcPage).input.add(btn)
        }
        setString()
    }
    fun addAction(button: ActionButton, position: Int){
        actual.add(position,button)
        setString()
    }
    fun removeAction(position: Int){
        actual.removeAt(position)
        setString()
    }
    fun removeAction(){
        if (actual.size > 0){
            actual.removeAt(actual.size-1)
            setString()
        }
    }
    fun setString(){
        setText(ParseUtils.createMathInputString(actual))
    }
    fun clear(){
        actual.clear()
        setText("")
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        //Scrolls to the start of the View
            while (canScrollHorizontally(-1)) scrollBy(-10,0)
        //Scrolls to the end of the View. I do realize this is a half-assed solution. But if knew me in real life, you'd know that it comes naturally to me.
            while (canScrollHorizontally(1)) scrollBy(10,0)

    }
}