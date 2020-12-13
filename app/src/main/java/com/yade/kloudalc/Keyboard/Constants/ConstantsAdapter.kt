package com.yade.kloudalc.Keyboard.Constants

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.icu.util.UniversalTimeScale.toLong
import android.opengl.Visibility
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.R.id.parent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import com.yade.kloudalc.Kactivity
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.ButtonManager
import com.yade.kloudalc.Keyboard.Constant
import com.yade.kloudalc.KloudalcApp
import com.yade.kloudalc.R
import com.yade.kloudalc.R.array.preferences_constant_gravity_options_data
import kotlinx.android.synthetic.main.constant_child.view.*
import kotlinx.android.synthetic.main.constant_group.view.*
import org.mariuszgromada.math.mxparser.parsertokens.RandomVariable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by tiitu on 25.2.2018.
 */
class ConstantsAdapter : AbstractExpandableItemAdapter<ConstantsAdapter.groupHolder, ConstantsAdapter.childHolder> {

    var list = ButtonManager.getConstants()
    var context: Activity? = null
    val random = Random()

    constructor(context: Activity) : super() {
        this.context = context
        setHasStableIds(true)
    }

    class childHolder : AbstractExpandableItemViewHolder {
        var Title = itemView.constant_child_title
        var description = itemView.constant_child_description
        var value = itemView.constant_child_value

        constructor(itemView: View?) : super(itemView) {
            Title = itemView?.constant_child_title
            description = itemView?.constant_child_description
            value = itemView?.constant_child_value
            itemView?.constant_child_help?.setBackgroundColor(Color.TRANSPARENT)
            itemView?.constant_child_copy?.setBackgroundColor(Color.TRANSPARENT)
            itemView?.constant_child_help?.visibility = View.GONE
        }


    }

    class groupHolder : AbstractExpandableItemViewHolder {
        var Title = itemView.constant_group_title

        constructor(itemView: View?) : super(itemView)
    }

    override fun onBindGroupViewHolder(holder: groupHolder?, groupPosition: Int, viewType: Int) {
        //Changes the constraints if necessary:
        val set = ConstraintSet()
        val hold = holder?.itemView as ConstraintLayout
        set.clone(hold)
        when(PreferenceManager.getDefaultSharedPreferences(KloudalcApp.instance).getString("constants_gravity","0")){
            "0" -> {
                set.clear(R.id.constant_group_title, ConstraintSet.END)
            }
            "2" -> {
                set.clear(R.id.constant_group_title, ConstraintSet.START)
            }
        }
        set.applyTo(holder?.itemView as ConstraintLayout)

        when (groupPosition) {
            0 -> holder?.Title?.setText("Math")
            1 -> holder?.Title?.setText("Physics")
            2 -> holder?.Title?.setText("Chemistry")
        }
        holder?.Title?.setTextColor(KloudalcApp.instance.resources.getColor(R.color.colorSecondary))
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return list.size
    }

    override fun onBindChildViewHolder(holder: childHolder?, groupPosition: Int, childPosition: Int, viewType: Int) {
        val button = list[groupPosition][childPosition]
        holder?.Title?.setText(button.Name)
        holder?.description?.setText(button.description)
        holder?.value?.setText(button.value)

        holder?.itemView?.constant_child_copy?.setOnClickListener { view ->
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Constant", button.value)
            clipboard.primaryClip = clip
        }
        holder?.Title?.setOnClickListener { view ->
            (context as Kactivity).currentFragment?.buttonClicked(button)
        }
    }

    override fun getChildCount(groupPosition: Int): Int {
        return list[groupPosition].size
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        var id = 0
        for (group in 0..groupPosition) {
            id += getChildCount(group)
        }
        id += childPosition
        return id.toLong()
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): groupHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.constant_group, parent, false)
        return groupHolder(v)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): childHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.constant_child, parent, false)
        return childHolder(v)
    }

    override fun onCheckCanExpandOrCollapseGroup(holder: groupHolder?, groupPosition: Int, x: Int, y: Int, expand: Boolean): Boolean {
        return true
    }
}