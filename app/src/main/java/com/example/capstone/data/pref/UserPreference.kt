package com.example.capstone.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    private val TOKEN_KEY = stringPreferencesKey("token")
    suspend fun saveSession(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun getSession(): Flow<String> {
        return dataStore.data.map { preferences ->
//            UserModel(
//                preferences[TOKEN_KEY] ?: "null"
//            )
            preferences[TOKEN_KEY] ?: "null"
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
//            preferences.remove(USER_NAME)
            preferences.remove(TOKEN_KEY)
//            preferences.clear()
        }
    }

//    fun getThemeSetting(): Flow<Boolean> {
//        return dataStore.data.map { preferences ->
//            preferences[THEME_KEY] ?: false
//        }
//    }
//
//    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[THEME_KEY] = isDarkModeActive
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}