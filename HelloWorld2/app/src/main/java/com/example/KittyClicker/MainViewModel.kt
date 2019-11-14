package com.example.KittyClicker


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repo = CountRepo(application.applicationContext) //getting the repo that holds these methods below - abstracting the layers out
    private val factRepo = FactRepository()
    fun getUserCount(name: String) = repo.getUserCount(name) //this will return username passed in at login stored in livedata
    fun getFact() = factRepo.getFact()
    fun setUserCount(name: String, count: Long) = repo.setUserCount(name, count) //this will set livedata count to what is passed in

}