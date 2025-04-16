package com.example.expandablefragment

import androidx.fragment.app.Fragment

abstract class FragmentConfigurator : Fragment() {
    abstract fun save()
    abstract fun update()
    abstract fun delete()

}