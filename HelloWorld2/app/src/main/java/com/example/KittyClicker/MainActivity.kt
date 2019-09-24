package com.example.KittyClicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var kittyCounter: Long = 0
    fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
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
            kittyCounter++
            TotalCount.text = kittyCounter.toString()
            if (kittyCounter >= 10) {
                Title.text = "YUMMY FOOD"
                catFood.visibility = View.VISIBLE
                catFood.setOnClickListener {
                    if (kittyCounter >= 10) {
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

    }

    companion object {
        private const val kittyCounterKey = "kittyCounterKey"

    }

}
