package com.mycompany.myapplication.utilities

import android.content.Context
import com.mycompany.myapplication.workers.App

object Preferences {

        private const val PREFS_FILE = "FILE_KEY"
        private const val PREFS_EMAIL = "EMAIL_KEY"
        private const val PREFS_PASSWORD = "PASSWORD_KEY"

    fun writeLogin(email: String) {
        val sharedPref = App.applicationContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(PREFS_EMAIL, email)
            apply()
        }
    }

    fun writePassword(password: String) {
            val sharedPref = App.applicationContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString(PREFS_PASSWORD, password)
                apply()
            }
        }

        fun readLogin() : String {
            val sharedPref = App.applicationContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

            return sharedPref.getString(PREFS_EMAIL, "") ?: ""
        }

        fun readPassword() : String {
            val sharedPref = App.applicationContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

            return sharedPref.getString(PREFS_PASSWORD, "") ?: ""
        }

}