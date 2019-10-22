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

    private val repo = CountRepo(application.applicationContext) //getting the repo that holds these methods below - abstracting the layers out

    fun getUserCount(name: String) = repo.getUserCount(name) //this will return username passed in at login stored in livedata

    fun setUserCount(name: String, count: Long) = repo.setUserCount(name, count) //this will set livedata count to what is passed in

}