package com.elyric.ricledger.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.elyric.ricledger.R
import com.elyric.ricledger.databinding.ActivityMainBinding
import com.elyric.ricledger.ui.view.custom.AppToolBar
import com.elyric.ricledger.ui.view.custom.AppToolBarBinder
import com.elyric.ricledger.ui.view.custom.AppToolBarDispatcher
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val dispatcher = AppToolBarDispatcher()
    private val binding
        get() = _binding!!
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var topBar: AppToolBar
    private lateinit var binder: AppToolBarBinder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        _binding = ActivityMainBinding.bind(findViewById(R.id.activity_main))
        bottomNav = binding.bottomNav
        topBar = binding.appToolBar
        binder = AppToolBarBinder(topBar,bottomNav)
        setupNavigationUI()
        lifecycleScope.launch {
            // 当 Lifecycle 处于 STARTED 或更高状态时，会收集 Flow
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dispatcher.state.collect { state ->
                    binder.bind(state)
                }
            }
        }

    }
    fun setupNavigationUI() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment)
                .navController
        // 让 BottomNavigationView 与 navController 联动
        bottomNav.setupWithNavController(navController)

    }
    fun getToolbarDispatcher(): AppToolBarDispatcher = dispatcher

}