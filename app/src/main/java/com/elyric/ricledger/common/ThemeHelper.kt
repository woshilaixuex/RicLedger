package com.elyric.ricledger.common

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// dataStore 键值存储
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

object ThemeHelper {

    private val THEME_KEY = stringPreferencesKey("theme")

    suspend fun setTheme(context: Context, mode: ThemeMode) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode.name
        }
        applyTheme(mode)
    }

    fun applyTheme(mode: ThemeMode) {
        AppCompatDelegate.setDefaultNightMode(
            when (mode) {
                ThemeMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                ThemeMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                ThemeMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }
    suspend fun getTheme(context: Context): ThemeMode {
        val prefs = context.dataStore.data.first()
        val modeName = prefs[THEME_KEY] ?: ThemeMode.SYSTEM.name
        return ThemeMode.valueOf(modeName)
    }
}

