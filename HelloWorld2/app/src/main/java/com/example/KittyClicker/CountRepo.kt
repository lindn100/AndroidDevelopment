package com.example.KittyClicker

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences


class CountRepo(context: Context) {
    private val preferences: SharedPreferences //how we are obtaining data persistance
    private val liveSharedPreferences: LiveSharedPreferences //using the live version to save livedata

    init {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE) //initializing preferences
        liveSharedPreferences = LiveSharedPreferences(preferences) //intializing sharedpreferences
    }

    fun setUserCount(name: String, count: Long) {
        preferences.edit().putLong(name, count).apply() //editting the preference, put in the count using the name as the key
    }

    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) {
            it[name] } //using a transformation map to return a map of usernames & longs, which will help us get multiple usernames into this map

    companion object {
        private const val PREFS = "clickCounts"
    }
}