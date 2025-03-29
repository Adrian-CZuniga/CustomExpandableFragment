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

    protected open var expandableNameTittle : String = "Categories Title"

    protected open var iconResId : Int = R.drawable.baseline_image_not_supported_24

    init {
        initExpandableFragment()

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ExpandableFragment, 0, 0)
            setTittle(typedArray.getString(R.styleable.ExpandableFragment_tittle_categories) ?: "Categories Title")
            setIconCategories(typedArray.getResourceId(R.styleable.ExpandableFragment_icon_categories, R.drawable.baseline_image_not_supported_24))
            typedArray.recycle()

        }
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

    fun setTittle(tittle: String){
        expandableNameTittle = tittle
        textNameCategories.text = tittle
    }

    fun setIconCategories(iconCategoriesResId: Int){
        iconResId = iconCategoriesResId
        iconCategories.setImageResource(iconResId)
    }

    protected fun expandFragment(){
        fragmentContainer.visibility = VISIBLE
        isExpanded = false
    }
    protected fun reduceFragment(){
        isExpanded = true
        fragmentContainer.visibility = GONE
    }

    protected fun loadFragment() {
        if (fragment != null) {
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction().apply {
                add(fragmentContainer.id, fragment!!)
                commit()
            }
        }
    }
}