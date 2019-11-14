package com.example.KittyClicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
/*import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds*/
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel //initialize ViewModel
//    private lateinit var mInterstitialAd: InterstitialAd

    //main counter for app
    private var kittyCounter: Long = 0
    //username getting passed via intents from the Login Activity
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) { //method that starts on app creation
        super.onCreate(savedInstanceState) //call the default on create method first
        setContentView(R.layout.activity_main) //set the view to our R.layout.activity_main


        /*MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())*/

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java) //load in the View Model
        mainViewModel.getUserCount(getUsername()).observe(this, androidx.lifecycle.Observer {
            updateCounter(it)
        }) //mainviewmodel gets the count by passing in the username and calling the method updateCounter


        cat.setOnClickListener{//setting an onclick listener to my cat picture (bella hella cute)
            ++kittyCounter  //on button click, increment the count
            mainViewModel.setUserCount(getUsername(), kittyCounter) //set the livedata count by passing in username and the new count
            meow.visibility = View.INVISIBLE //set the words 'meow' to invisible - should only be visible when cat gets fed
            if(kittyCounter < 10) { //default less than 10 text and no cat food unlock
                Title.text = "Kitty Clicker" //default title text
                catFood.visibility = View.INVISIBLE //keep cat food invisible until over 10
            } else {
                mainViewModel.getFact().observe(this, androidx.lifecycle.Observer {

                    Title.text = it.text //next tier unlocked, title is now YUMMY FOOD
                })
                catFood.visibility = View.VISIBLE //food now UNLOCKED
                catFood.setOnClickListener {//now that food is unlocked, make it clickable to feed bella
                   /* if (mInterstitialAd.isLoaded) {
                        mInterstitialAd.show()
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.")
                    }*/
                    meow.visibility = View.VISIBLE //bought some cat food? that makes bella happy & meow
                    kittyCounter -= 10 //but you lose some points for it
                    mainViewModel.setUserCount(getUsername(), kittyCounter) //updating the livedata
                    if(kittyCounter < 10) { //if you get back to under 10, you lose the ability to buy catfood & the title
                        Title.text = "Kitty Clicker" //default title
                        catFood.visibility = View.INVISIBLE //no more cat food
                    }

                }
            }
        }

    }

    private fun updateCounter(count: Long) { //this is called when we update the live data model
        kittyCounter = count //setting the internal counter to the livedata
        TotalCount.text = kittyCounter.toString() //updating the number on the screen
    }
    /*private var kittyCounter: Long = 0
    fun getStore() = getPreferences(Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        val model = ViewModelProviders.of(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user = intent.extras?.getString("username", "default")

        if (savedInstanceState != null) {
            kittyCounter = savedInstanceState.getLong(user, 0)
            TotalCount.text = kittyCounter.toString()
            if (kittyCounter < 10) {
                Title.text = "Kitty Clicker"
            } else {
                Title.text = "YUMMY FOOD"
                catFood.visibility = View.VISIBLE
            }
        } else {
            kittyCounter = getStore().getLong(user, 0)
            TotalCount.text = kittyCounter.toString()
            if (kittyCounter < 10) {
                Title.text = "Kitty Clicker"
            } else {
                Title.text = "YUMMY FOOD"
                catFood.visibility = View.VISIBLE
            }
        }

        var catFoodCounter:Long = 0
        /*Timer().schedule(1000) {
            kittyCounter += (5 * catFoodCounter)
            TotalCount.text = kittyCounter.toString()
        }*/

        cat.setOnClickListener {
            //cat.toggleVisibility()
            //cat.rotate90()
            meow.visibility = View.INVISIBLE
            kittyCounter++
            TotalCount.text = kittyCounter.toString()
            if (kittyCounter >= 10) {
                Title.text = "YUMMY FOOD"
                catFood.visibility = View.VISIBLE
                catFood.setOnClickListener {
                    if (kittyCounter >= 10) {
                        meow.visibility = View.VISIBLE
                        kittyCounter -= 10
                        TotalCount.text = kittyCounter.toString()
                        catFoodCounter += 1
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        var user = intent.extras?.getString("username", "default")
        getStore().edit().putLong(user, kittyCounter).apply()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run{
            var user = intent.extras?.getString("username", "default")
            putLong(user, kittyCounter)


        }

        super.onSaveInstanceState(outState)

    }*/

    /*
    AdMob - Google SDK

     */

}
