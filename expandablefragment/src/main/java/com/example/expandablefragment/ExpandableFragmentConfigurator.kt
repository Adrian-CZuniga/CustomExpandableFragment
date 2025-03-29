package com.example.expandablefragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log

open class ExpandableFragmentConfigurator
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConfigurableExpandableFragment(context, attrs, defStyleAttr) {
    private val tag = ExpandableFragmentConfigurator::class.java.simpleName
    override var expandableNameTittle: String = "Configurator Categories Title"

    fun saveConfiguration(){
        Log.i(tag, "Saving configuration")
        saveFragmentConfiguration()
    }
    fun updateConfiguration(){
        Log.i(tag, "Updating configuration")
        updateFragmentConfiguration()
    }
    fun deleteConfiguration(){
        Log.i(tag, "Deleting configuration")
        deleteFragmentConfiguration()
    }
}