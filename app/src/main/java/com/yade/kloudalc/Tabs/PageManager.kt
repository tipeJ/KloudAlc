package com.yade.kloudalc.Tabs

import com.yade.kloudalc.Fragments.CalcFragment
import com.yade.kloudalc.Fragments.CalcFragment.Factory.longKey
import java.util.*
import kotlin.collections.ArrayList

class PageManager private constructor(){

    private object Holder{
        val instance = PageManager()
    }

    companion object {
        val instance: PageManager by lazy { Holder.instance }

        val kCalcPage = "Page.CalcPage"
        val kConvPage = "Page.ConversionPage"
    }

    var tablist = ArrayList<Page>()

    val random = Random()

    var currentPage: Page?= null

    var usedIntegers = 0

    fun newPage(type: String): Long{

        //TODO: add new pages
        val apage : Page
        when (type){
            kCalcPage -> {
                apage = CalcPage()
            }
            else ->{
                apage = CalcPage()
            }
        }
        apage.id = usedIntegers.toLong()
        usedIntegers++

        tablist.add(apage)
        return apage.id
    }

    fun findPage(id: Long):Page? {
        return tablist.find { page -> page.id == id }
    }

    fun removePage(position: Int){ tablist.removeAt(position)}

    fun removePage(id: Long){
        tablist.remove(findPage(id))
    }

    fun movePage(fromPosition: Int, toPosition: Int){
        if (fromPosition < toPosition) {
            Collections.swap(tablist,fromPosition,fromPosition+1)
        } else {
            Collections.swap(tablist,fromPosition,fromPosition-1)
        }
    }

    fun currentPageIndex(): Int{
        return tablist.indexOf(currentPage)
    }
}