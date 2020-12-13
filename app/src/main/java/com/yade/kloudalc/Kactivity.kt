package com.yade.kloudalc

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.ActivityChooserView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yade.kloudalc.Activities.SettingsActivity
import com.yade.kloudalc.Fragments.CalcFragment
import com.yade.kloudalc.Interfaces.boardCallBack
import com.yade.kloudalc.Keyboard.ActionButton
import com.yade.kloudalc.Keyboard.Mathboard
import com.yade.kloudalc.Presenter.CalcPresenter
import com.yade.kloudalc.Tabs.CalcPage
import com.yade.kloudalc.Tabs.Callback.TabCallBack
import com.yade.kloudalc.Tabs.Page
import com.yade.kloudalc.Tabs.PageManager
import com.yade.kloudalc.Tabs.TabAdapter
import com.yade.kloudalc.Utils.LicenseUtils
import com.yade.kloudalc.Variables.VariableManager
import kotlinx.android.synthetic.main.activity_kactivity.*

class Kactivity : AppCompatActivity() {
    val permimssion_int = 0
    var currentFragment: boardCallBack? = null
        get() {
            return (supportFragmentManager.findFragmentById(R.id.kactivity_fragment)) as boardCallBack
        }

    val adapter = TabAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kactivity)

        //TODO: Fix this for future implementations of new types of Page
        if (PageManager.instance.currentPage == null) {
            PageManager.instance.currentPage = PageManager.instance.findPage(PageManager.instance.newPage(PageManager.kCalcPage)) as CalcPage
        }

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            kactivity_tabar.layoutManager = TabAdapter.lmanager(this, LinearLayout.HORIZONTAL, false)
        } else {
            kactivity_tabar.layoutManager = TabAdapter.lmanager(this, LinearLayout.VERTICAL, true)
        }


        if (supportFragmentManager.findFragmentById(R.id.kactivity_fragment) == null) {
            val mathf = CalcFragment()
            currentFragment = mathf

            supportFragmentManager.beginTransaction().replace(R.id.kactivity_fragment, mathf)
                    .commit()
            supportFragmentManager.executePendingTransactions()

            kactivity_tabar.invalidate()
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val wallpaperIf = prefs.getBoolean("wallpaper_switch", false)

        if (wallpaperIf) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.INTERNET)) {
                    Log.i("fuck", "fuckity")
                } else {
                    Log.i("fuck", "unfuckity")
                    ActivityCompat.requestPermissions(this, arrayOf(
                            Manifest.permission.INTERNET
                    ), permimssion_int)
                }

            } else {
                val options = RequestOptions()
                options.centerCrop()

                Log.i("fuck", "yeah")
                Glide.with(this)
                        .load(prefs.getString("wallpaper_url", "https://i.imgur.com/Tfbu61O.jpg"))
                        .apply(options)
                        .into(kactivity_bg)
            }
        } else {
            kactivity_bg.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        }

        kactivity_menu.setBackgroundColor(Color.argb(0, 0, 0, 0))
        kactivity_menu.imageTintList = ColorStateList.valueOf(Color.WHITE)
        kactivity_menu.setOnClickListener { view ->
            val popmenu = PopupMenu(this, view)
            popmenu.menuInflater.inflate(R.menu.kactivity_menui, popmenu.menu)

            //Disabling variable addition if license is lite
            if (!LicenseUtils.hasplus()) {
                popmenu.menu.findItem(R.id.kactivity_menu_new_variable).isEnabled = false
            }

            popmenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem?.itemId) {
                    R.id.kactivity_menu_new_variable -> {
                        if (currentFragment is CalcFragment) {
                            val editText = EditText(this)
                            editText.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

                            val builder = AlertDialog.Builder(this)
                            builder.setTitle("Enter variable name:")
                            builder.setView(editText)

                            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                                val input = (currentFragment as CalcFragment).input?.actual?.toMutableList()
                                if (!editText.text.toString().isEmpty() && input != null) {
                                    val variable = VariableManager.Variable()
                                    variable.Name = editText.text.toString()
                                    variable.Value = ArrayList<ActionButton>()
                                    input.forEach { button ->
                                        variable.Value?.add(button)
                                    }
                                    VariableManager.instance.varMap.add(variable)

                                    val intent = Intent(KloudalcApp.instance, this.javaClass)
                                    intent.action = CalcPresenter.VAR_CHANGED
                                    sendBroadcast(intent)
                                    dialogInterface.dismiss()
                                }
                            })
                            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                                dialogInterface.cancel()
                            })
                            builder.create().show()
                        }
                        true
                    }
                    R.id.kactivity_menu_wolfram -> {
                        Toast.makeText(this, "yeah", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.kactivity_menu_upgrade -> {
                        val alert = AlertDialog.Builder(this)

                        val wv = WebView(this)
                        wv.loadUrl("file:///android_asset/Upgrade.html")
                        wv.webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                                view?.loadUrl(url)

                                return true
                            }
                        }
                        alert.setView(wv)
                        alert.setNegativeButton("Close", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                p0?.dismiss()
                            }
                        })

                        val dialog = alert.create()
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.show()

                        true
                    }
                    R.id.kactivity_menu_settings -> {

                        val intent = Intent(this@Kactivity, SettingsActivity::class.java)

                        startActivity(intent)

                        true
                    }
                    else -> false
                }
            }
            popmenu.show()
        }

        if (!LicenseUtils.hasplus()) {
            kactivity_tabar.visibility = View.GONE
            return
        }

        adapter.setHasStableIds(true)
        adapter.par = this

        kactivity_tabar.adapter = adapter
        kactivity_tabar.invalidate()
        val callBack = TabCallBack(adapter)
        val touchHelper = ItemTouchHelper(callBack)
        touchHelper.attachToRecyclerView(kactivity_tabar)

    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        val transaction = supportFragmentManager.beginTransaction()

        updateCurrentPageIndex()
        if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val mathf = CalcFragment()
            currentFragment = mathf

            transaction.replace(R.id.kactivity_fragment, mathf)
                    .commit()
            supportFragmentManager.executePendingTransactions()

        } else if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val mathf = CalcFragment.newInstance(Mathboard.typeFun)
            currentFragment = mathf

            transaction
                    .replace(R.id.kactivity_fragment, mathf)
                    .commit()
            supportFragmentManager.executePendingTransactions()

        }
        if (!LicenseUtils.hasplus()) {
            kactivity_tabar.visibility = View.GONE
            return
        }
    }

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    fun updateCurrentPageIndex() {
        PageManager.instance.tablist.forEach { tab ->
            val value = kactivity_tabar.findViewHolderForAdapterPosition(PageManager.instance.tablist.indexOf(tab))
            if (PageManager.instance.tablist.indexOf(tab) == PageManager.instance.currentPageIndex() && value != null) {
                (value as TabAdapter.TabHolder).text?.setTextColor(Color.CYAN)
            } else if (value != null) {
                (value as TabAdapter.TabHolder).text?.setTextColor(Color.WHITE)
            }
        }
    }

    fun switchPages(position: Int) {
        val oldPage = PageManager.instance.currentPage
        val newPage = PageManager.instance.tablist[position]

        if (PageManager.instance.tablist.indexOf(oldPage) == PageManager.instance.tablist.indexOf(newPage)) {
            return
        }
        if (oldPage!!.type == newPage.type) {
            PageManager.instance.currentPage = newPage
            updateCurrentPageIndex()
            currentFragment?.loadPage(newPage)
        }
    }
}
