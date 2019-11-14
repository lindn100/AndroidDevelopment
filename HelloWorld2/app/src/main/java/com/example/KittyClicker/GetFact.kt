package com.example.KittyClicker

import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GetFact {
    @GET("facts/random") //api.giphy.com/v1/stickers/search
    fun getFact(@Query("animal_type") type:String,
            @Query("amount") amount:Int):Call<Fact>
            //more fields if u want

    companion object {
        fun create(): GetFact {
            val retrofit = Retrofit.Builder().
                    addConverterFactory(GsonConverterFactory.create()
                    ).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://cat-fact.herokuapp.com").build()

            return retrofit.create(GetFact::class.java)
        }
    }
}