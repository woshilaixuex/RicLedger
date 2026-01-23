package com.elyric.ricledger.ui.view.custom

import android.view.View.OnClickListener

/**
 * 用于描述自定义ToolBar，底部导航与Fragment的对应关系
 *
 */
sealed class AppToolBarState {
    abstract val fragmentId: Int
    abstract val title: String
    open val topicEnable: Boolean = true
    open val backEnabled: Boolean = true
    open val titleVisible: Boolean = true
    open val bottomNavVisible: Boolean = true
    open val onBack: (() -> Unit)? = null

    /**
     * 最上层视图
     */
    data class TopFragment(
        override val fragmentId: Int,
        override val title: String
    ) : AppToolBarState() {
        override val topicEnable = false
        override val backEnabled = false
        override val titleVisible: Boolean = false
        override val bottomNavVisible: Boolean = true
    }

    /**
     * 有上层的视图
     */
    data class NormalFragment(
        override val fragmentId: Int,
        override val title: String,
        override val onBack: (() -> Unit)? = null
    ) : AppToolBarState() {
        override val backEnabled: Boolean = onBack != null
        override val titleVisible: Boolean = true
        override val bottomNavVisible: Boolean = true
        fun withOnBack(action: () -> Unit): NormalFragment {
            return copy(onBack = action)
        }
    }

    /**
     * 全屏模式
     */
    data class FullScreen(
        override val fragmentId: Int
    ) : AppToolBarState() {
        override val title = ""
        override val topicEnable = false
        override val backEnabled = false
        override val titleVisible = false
        override val bottomNavVisible = false
    }

    /**
     * 自定义模式
     */
    data class DefaultScreen(
        override val fragmentId: Int,
        override val title: String,
        override val topicEnable: Boolean,
        override val backEnabled:Boolean,
        override val titleVisible: Boolean,
        override val bottomNavVisible: Boolean,
        override val onBack:(() -> Unit)? = null
    ): AppToolBarState() {
        fun withOnBack(action: () -> Unit): DefaultScreen {
            return copy(onBack = action)
        }
    }

    override fun toString(): String {
        return "AppToolBarState(fragmentId=$fragmentId, title='$title', backEnabled=$backEnabled, titleVisible=$titleVisible, bottomNavVisible=$bottomNavVisible, onBack=$onBack)"
    }


}
