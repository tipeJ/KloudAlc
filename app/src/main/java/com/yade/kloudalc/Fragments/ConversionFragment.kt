package com.yade.kloudalc.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.Mathboard
import com.yade.kloudalc.R
import com.yade.kloudalc.Tabs.ConversionPage
import com.yade.kloudalc.Tabs.Page
import com.yade.kloudalc.Utils.Calculator
import com.yade.kloudalc.Views.MathInput

class ConversionFragment : Fragment, boardCallBack {
    var upperInput = view?.findViewById<MathInput>(R.id.conversion_upperinput)
    var lowerInput = view?.findViewById<MathInput>(R.id.conversion_lowerinput)

    var upperUnitSwitcher = view?.findViewById<ImageButton>(R.id.conversion_upperUnitSwitcher)
    var lowerUnitSwitcher = view?.findViewById<ImageButton>(R.id.conversion_lowerUnitSwitcher)

    var upperUnit = view?.findViewById<TextView>(R.id.conversion_upperUnit)
    var lowerUnit = view?.findViewById<TextView>(R.id.conversion_lowerUnit)

    override val board = Mathboard()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_coversion, container, false)

        upperInput?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val added = Calculator.calculateDecimal(currentInput()!!.actual)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        lowerInput?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        return v
    }

    constructor() : super()

    override fun buttonClicked(button: ActionButton) {
        currentInput()?.addAction(button)
    }

    fun currentInput(): MathInput? {
        return if (upperInput!!.isFocused) upperInput else lowerInput
    }

    fun notCurrentInput(): MathInput? {
        return if (upperInput!!.isFocused) lowerInput else upperInput
    }
    fun currentUnit() : ImageButton? {
        return if (currentInput() == upperInput) upperUnitSwitcher else lowerUnitSwitcher
    }

    override fun buttonRemoved(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun move(amount: Int, direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPage(page: Page) {
        if (page is ConversionPage){

        }
    }
}