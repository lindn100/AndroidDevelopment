package com.example.KittyClicker

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginUsernameField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //unused
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //unused
            }

            override fun afterTextChanged(p0: Editable?) {
                //unused
            }
        })
        //setting a listener to a button which will start an activity to create an intent to pass the username which resides in loginUsernameField
        loginButton.setOnClickListener{startActivity(Intent(this, MainActivity::class.java).apply { putExtra("username", loginUsernameField.text.toString())})}
    }


}
