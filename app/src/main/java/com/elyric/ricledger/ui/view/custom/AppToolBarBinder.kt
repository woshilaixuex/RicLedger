package com.elyric.ricledger.ui.view.custom

import android.util.Log
import android.view.View

/**
 * 主要state绑定到视图的逻辑
 * @see AppToolBar
 * @see AppToolBarBinder
 */
interface AppToolBarOwner {
    fun provideToolBarState(): AppToolBarState
}
class AppToolBarBinder(
    private val toolBar: AppToolBar,
    private val bottomNav: View
) {
    fun bind(state: AppToolBarState) {
        Log.i("AppToolBarBinder",state.toString())
        if (!state.topicEnable){
            toolBar.visibility = View.GONE
        }else {
            toolBar.visibility = View.VISIBLE
            toolBar.setTitle(state.title)
            toolBar.showBackButton(state.backEnabled)
            toolBar.showTitle(state.titleVisible)
            toolBar.setOnBackClickListener {
                state.onBack?.invoke()
            }
        }
        bottomNav.visibility =
            if (state.bottomNavVisible) View.VISIBLE else View.GONE
    }
}