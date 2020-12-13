package com.yade.kloudalc.Variables

import com.yade.kloudalc.Keyboard.ActionButton

class VariableManager private constructor(){
    init {

    }

    private object Holder{
        val instance = VariableManager()
    }

    companion object {
        val instance: VariableManager by lazy { Holder.instance }
    }

    var varMap = ArrayList<Variable>()


    class Variable {
        var Name : String ?= null
        var Value : ArrayList<ActionButton> ?= null
    }
}