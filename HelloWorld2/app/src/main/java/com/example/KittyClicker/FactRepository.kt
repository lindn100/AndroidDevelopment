package com.example.KittyClicker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactRepository {
    private val getFact: GetFact = GetFact.create()

    fun getFact(): LiveData<Fact> {
        val data = MutableLiveData<Fact>()
        getFact.getFact("cat", 1).enqueue(object: Callback<Fact> {
            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                Log.d("TEST", "${response.raw().body()}")
                data.value = response.body()
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {}
        }
        )
        return data
    }
}