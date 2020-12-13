package com.yade.kloudalc.Variables

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Keyboard.NumButton
import com.yade.kloudalc.R
import com.yade.kloudalc.Utils.ParseUtils
import kotlinx.android.synthetic.main.variable.view.*

class VariableAdapter : RecyclerView.Adapter<VariableAdapter.VariableHolder> {
    var callBack : boardCallBack ?= null
    constructor() : super()

    class VariableHolder : RecyclerView.ViewHolder {
        var name = itemView.variable_name
        var value = itemView.variable_value

        constructor(itemView: View?) : super(itemView){
            name = itemView?.variable_name
            value = itemView?.variable_value
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariableHolder {
        val holder = LayoutInflater.from(parent?.context).inflate(R.layout.variable,null)
        return VariableHolder(holder)
    }

    override fun getItemCount(): Int {
        return VariableManager.instance.varMap.size
    }

    override fun onBindViewHolder(holder: VariableHolder, position: Int) {
        holder?.name?.text = VariableManager.instance.varMap[position].Name
        holder?.value?.text = ParseUtils.createVariableValue(VariableManager.instance.varMap[position].Value)

        holder?.itemView?.setOnClickListener { view ->
            val data  = VariableManager.instance.varMap[position].Value
            data?.forEach { button ->
                callBack?.buttonClicked(button)
            }
        }
    }
}