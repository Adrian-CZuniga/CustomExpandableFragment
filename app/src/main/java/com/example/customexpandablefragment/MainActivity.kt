package com.example.customexpandablefragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expandablefragment.ExpandableFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val expandableFragment = findViewById<ExpandableFragment>(R.id.expandable_fragment)

        //expandableFragment.setTittle("Hello World!")
        //expandableFragment.setIconCategories(R.drawable.ic_launcher_background)
    }
}