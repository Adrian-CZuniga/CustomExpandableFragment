package com.example.expandablefragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

open class ExpandableFragment
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val tag = ExpandableFragment::class.java.simpleName
    protected var isExpanded = true
    open var fragment : Fragment? = null

    private lateinit var textNameCategories: TextView
    private lateinit var iconCategories: ImageView
    private lateinit var iconControl: ImageView
    private lateinit var fragmentContainer: FrameLayout

    open var nameText : String = ""
        set(value) {
            field = value
            textNameCategories.text = value
        }

    open var iconResId : Int = R.drawable.baseline_image_not_supported_24
        set(value) {
            field = value
            iconCategories.setImageResource(value)
        }

    init {
        initExpandableFragment()
    }

    private fun initExpandableFragment(){
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.expandable_fragment, this, true)

        textNameCategories = view.findViewById(R.id.text_name_categories)
        iconCategories = view.findViewById(R.id.icon_categories)
        iconControl = view.findViewById(R.id.icon_expanded)
        fragmentContainer = view.findViewById(R.id.fragment_container)
        // Generate a unique ID for the fragmentContainer
        fragmentContainer.id = View.generateViewId()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        loadFragment()
        setOnClickListeners()
    }

    protected fun setOnClickListeners(){
        iconControl.setOnClickListener {
            if(isExpanded){
                expandFragment()
            }else{
                reduceFragment()
            }
        }
    }

    protected fun expandFragment(){
        Log.i(tag, "Expanding fragment")
        fragmentContainer.visibility = VISIBLE
        isExpanded = false
    }
    protected fun reduceFragment(){
        Log.i(tag, "Collapse fragment")
        isExpanded = true
        fragmentContainer.visibility = GONE
    }

    protected fun loadFragment() {
        Log.i(tag, "Loading fragment: ${fragment?.toString()}")

        if (fragment != null) {
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().apply {
                add(fragmentContainer.id, fragment!!)
                commit().apply {
                    Log.i(tag, "Loaded fragment: ${fragment?.toString()}")
                }
            }
        }
    }
}