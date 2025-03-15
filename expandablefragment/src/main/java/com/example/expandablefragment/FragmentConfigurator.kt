package com.example.expandablefragment

import androidx.fragment.app.Fragment

abstract class FragmentConfigurator : Fragment() {
    protected abstract var configurator : Configurator?

    abstract fun save()
    abstract fun update()
    abstract fun delete()

    open fun attachConfigurator(cfg : Configurator){
        configurator = cfg
    }
}