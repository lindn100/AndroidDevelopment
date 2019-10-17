package com.example.KittyClicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var kittyCounter: Long = 0
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getUserCount(getUsername()).observe(this, androidx.lifecycle.Observer {
            updateCounter(it)
        })

        cat.setOnClickListener{
            ++kittyCounter
            mainViewModel.setUserCount(getUsername(), kittyCounter)
            meow.visibility = View.INVISIBLE
            if(kittyCounter < 10) {
                Title.text = "Kitty Clicker"
                catFood.visibility = View.INVISIBLE
            } else {
                Title.text = "YUMMY FOOD"
                catFood.visibility = View.VISIBLE
                catFood.setOnClickListener {
                    meow.visibility = View.VISIBLE
                    kittyCounter -= 10
                    mainViewModel.setUserCount(getUsername(), kittyCounter)
                    if(kittyCounter < 10) {
                        Title.text = "Kitty Clicker"
                        catFood.visibility = View.INVISIBLE
                    }

                }
            }
        }

    }

    private fun updateCounter(count: Long) {
        kittyCounter = count
        TotalCount.text = kittyCounter.toString()
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

}
