package com.yade.kloudalc.Activities

import android.content.Context
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import com.yade.kloudalc.R
import kotlinx.android.synthetic.main.activity_settings.view.*

class SettingsActivity : AppCompatActivity {
    constructor() : super()


    companion object {
        val KEY_PREF_WALLPAPER = "wallpaper_switch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        if (fragmentManager.findFragmentById(R.id.settingsactivity_content) == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.settingsactivity_content, SettingsFragment())
                    .commit()
        }
    }


    class SettingsFragment : PreferenceFragment {
        constructor() : super()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)

            preferenceScreen.findPreference("functions_enable_rowlength").setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener {
                override fun onPreferenceChange(p0: Preference?, p1: Any?): Boolean {
                    if (p1 == true) {
                        preferenceScreen.findPreference("functions_rowlength").isEnabled = true
                    } else {
                        preferenceScreen.findPreference("functions_rowlength").isEnabled = false
                    }
                    return true
                }
            })
//
//            (preferenceScreen.findPreference("functions_rowlength") as EditTextPreference).editText.inputType = InputType.TYPE_CLASS_NUMBER
//            preferenceScreen.findPreference("wallpaper_switch").setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener {
//                override fun onPreferenceChange(p0: Preference?, p1: Any?): Boolean {
//                    if (p1 == true) {
//                        preferenceScreen.findPreference("wallpaper_url").isEnabled = true
//                    } else {
//                        preferenceScreen.findPreference("wallpaper_url").isEnabled = false
//                    }
//                    return true
//                }
//            })

            (preferenceScreen.findPreference("functions_rowlength") as EditTextPreference).editText.inputType = InputType.TYPE_CLASS_TEXT
        }
    }

}