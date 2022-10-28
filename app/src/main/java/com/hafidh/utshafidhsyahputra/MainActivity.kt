package com.hafidh.utshafidhsyahputra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hafidh.utshafidhsyahputra.databinding.ActivityMainBinding
import com.hafidh.utshafidhsyahputra.fragment.HomeFragment
import com.hafidh.utshafidhsyahputra.fragment.ProfileFragment
import com.hafidh.utshafidhsyahputra.fragment.UTSFragment

class MainActivity : AppCompatActivity() {

    // mengimport fragment home,profile,uts
    val fragmentHome: Fragment = HomeFragment()
    val fragmentProfile: Fragment = ProfileFragment()
    val fragmentUts: Fragment = UTSFragment()
    //

    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = HomeFragment()

    private lateinit var buttonNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // menginisialisasi button navigation
        buttonNav()
    }

    private fun buttonNav() {
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentProfile).hide(fragmentProfile).commit()
        fm.beginTransaction().add(R.id.container, fragmentUts).hide(fragmentUts).commit()

        buttonNavigationView = binding.navView
        menu = buttonNavigationView.menu
        // saat start awal akan muncul fragment home
        menuItem = menu.getItem(0)
        menuItem.isChecked = true
        // mengatur button navigation
        buttonNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    callBackFragment(0, fragmentHome)
                }
                R.id.nav_profile -> {
                    callBackFragment(1, fragmentProfile)
                }
                R.id.nav_p4 -> {
                    callBackFragment(2, fragmentUts)
                }
            }
            false
        }
    }

    // mengatur fragment
    private fun callBackFragment(index: Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}