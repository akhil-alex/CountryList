package com.assignment.countrylist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.assignment.countrylist.R

class MainActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        fragment= CountryListFragment()
        replaceFragment(fragment as CountryListFragment)
    }

    private fun replaceFragment(fragment: CountryListFragment) //fragment replace
    {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
    }

}