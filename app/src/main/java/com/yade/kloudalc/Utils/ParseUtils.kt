package com.yade.kloudalc.Utils

import android.content.ClipboardManager
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.FuncButton
import com.yade.kloudalc.Keyboard.NumButton
import com.yade.kloudalc.Keyboard.UnitButton


object ParseUtils {

    fun createMathInputString(buttons: ArrayList<ActionButton>) : String{
        val builder = StringBuilder()
        buttons.forEach { b ->
            when(b){
                is FuncButton -> builder.append(b.getACTitle() + "(")
                is UnitButton -> builder.append("*" + b.getACTitle())
                else -> builder.append(b.getACTitle())
            }

        }
        return builder.toString()
    }
    fun createVariableValue(buttons: ArrayList<ActionButton>?) : String{
        val builder = StringBuilder()
        buttons?.forEach { b ->
            builder.append(b.getACTitle())

        }
        return builder.toString()
    }
    fun combineActionButtons(buttons: ArrayList<ActionButton>?): String{
        val builder = StringBuilder()
        buttons?.forEach { b ->
            builder.append(b.getInside())
        }
        return builder.toString()
    }

}