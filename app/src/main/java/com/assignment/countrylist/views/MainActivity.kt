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
        setSupportActionBar(findViewById(R.id.toolbar))
        fragment= CountryListFragment()
        replaceFragment(fragment as CountryListFragment)
    }

    private fun replaceFragment(fragment: CountryListFragment) //fragment replace
    {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.commit()
    }
    fun setActionBarTitle(title:String) //set action bar title
    {
        this.supportActionBar?.title=title
    }
}