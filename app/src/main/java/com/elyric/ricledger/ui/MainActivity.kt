package com.elyric.ricledger.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.elyric.ricledger.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var topBar: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bottomNav = findViewById(R.id.bottom_nav)
        topBar = findViewById(R.id.topic_bar)
        controlDisplay()
    }
    // 控件展示逻辑定义
    private val hideTopBarDestinations = setOf(
        R.id.splashFragment,
        R.id.addBillFragment,
        R.id.billListFragment,
        R.id.stateFragment,
        R.id.settingFragment,
    )
    private val hideBottomNavDestinations = setOf(
        R.id.addImplBillFragment,
        R.id.splashFragment
    )
    // 在设置控件控制器以及显示展示逻辑
    fun controlDisplay() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    as NavHostFragment).navController
        bottomNav.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.addBillFragment,
                R.id.billListFragment,
                R.id.stateFragment,
                R.id.settingFragment,
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isHideTopBar = destination.id in hideTopBarDestinations
            val isHideBottomNav = destination.id in hideBottomNavDestinations
            topBar.visibility = if (isHideTopBar) View.GONE else View.VISIBLE
            bottomNav.visibility = if (isHideBottomNav) View.GONE else View.VISIBLE
        }
    }

}