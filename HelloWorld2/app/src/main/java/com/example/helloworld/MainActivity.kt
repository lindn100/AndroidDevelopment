package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.helloworld.util.rotate90
import com.example.helloworld.util.toggleVisibility
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var kittyCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            kittyCounter = savedInstanceState.getInt(kittyCounterKey, 0)
        }
        //var vis:Boolean = false

        cat.setOnClickListener {
            //cat.toggleVisibility()
            //cat.rotate90()
            kittyCounter++
            TotalCount.text = "" + kittyCounter

            if (kittyCounter >= 10) {
                Title.text = "MEOW. YOU'VE WON"
            }

           /* if(vis) {
                cat.visibility = View.INVISIBLE
                vis = false
            } else {
                cat.visibility = View.VISIBLE
                vis = true
            }*/

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run{
            putInt(kittyCounterKey, kittyCounter)

        }

        super.onSaveInstanceState(outState)

    }

    companion object {
        private const val kittyCounterKey = "kittyCounterKey"

    }

}
