package com.example.expandablefragment

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment

abstract class ConfigurableExpandableFragment
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ExpandableFragment(context, attrs, defStyleAttr) {

    protected fun <T : Fragment> getFragment(fragmentClass: Class<T>): T? {
        return if (fragmentClass.isInstance(fragment)) {
            fragmentClass.cast(fragment)
        } else {
            null
        }
    }

    protected fun saveFragmentConfiguration() {
        val fragment = getFragment(FragmentConfigurator::class.java)
        fragment?.save()
    }

    protected fun updateFragmentConfiguration() {
        val fragment = getFragment(FragmentConfigurator::class.java)
        fragment?.update()
    }

    protected fun deleteFragmentConfiguration() {
        val fragment = getFragment(FragmentConfigurator::class.java)
        fragment?.delete()
    }
}