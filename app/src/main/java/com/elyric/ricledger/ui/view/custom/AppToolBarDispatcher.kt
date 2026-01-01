package com.elyric.ricledger.ui.view.custom

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppToolBarDispatcher {
    private val _state = MutableStateFlow<AppToolBarState>(AppToolBarState.FullScreen(0))
    val state: StateFlow<AppToolBarState> = _state

    fun publish(state: AppToolBarState) {
        _state.value = state
    }
}
