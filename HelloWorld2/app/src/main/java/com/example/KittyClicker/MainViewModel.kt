package com.example.KittyClicker


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class MainViewModel(application: Application) : AndroidViewModel(application) {
/*    var kittyCount: Int
        get() {
            return this.kittyCount
        }
        set(x) {
            this.kittyCount = x
        }



    val kittyCounter: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }*/

    private val repo = CountRepo(application.applicationContext)

    fun getUserCount(name: String) = repo.getUserCount(name)

    fun setUserCount(name: String, count: Long) = repo.setUserCount(name, count)

}