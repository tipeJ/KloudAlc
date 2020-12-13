package com.yade.kloudalc.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.ButtonRow
import com.yade.kloudalc.Keyboard.FuncButton
import com.yade.kloudalc.Keyboard.Mathboard

class MathButtonsAdapter : RecyclerView.Adapter<MathButtonsAdapter.RowHolder> {
    val TAG = "MathButtonsAdapter"

    var list: ArrayList<ArrayList<ActionButton>>
    var context: Context? = null
    var callBack : boardCallBack? = null

    constructor(context: Context, list : ArrayList<ArrayList<ActionButton>>) : super(){
        this.list = list
        this.context = context
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        if (holder!!.row!!.list.size != list.get(position).size){
            holder.row!!.setButtons(list[position].size)
        }
        holder.row!!.callBack = callBack
        holder.row!!.setButtonData(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val row = ButtonRow(context)
        row.orientation = LinearLayout.HORIZONTAL
        row.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,350 )
        return RowHolder(row)
    }

    class RowHolder : RecyclerView.ViewHolder{
        var row : ButtonRow? = null

        constructor(itemView: View?) : super(itemView){
            row = itemView as ButtonRow
        }
    }
}