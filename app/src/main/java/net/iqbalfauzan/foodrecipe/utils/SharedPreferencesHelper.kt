package net.iqbalfauzan.foodrecipe.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created by Iqbalmf on 2019-08-17
 */
class SharedPreferencesHelper {

    companion object {
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedPreferencesHelper? = null

        private var LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferencesHelper{
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }
}