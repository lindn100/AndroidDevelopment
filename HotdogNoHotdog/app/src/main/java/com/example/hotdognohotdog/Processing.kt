package com.example.hotdognohotdog

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_processing.*
import java.io.File
import java.io.IOException
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.example.videorecorder.MainActivity


class Processing : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_processing)
        val context = this
        val filename = "test.png"
        val sd = Environment.getExternalStorageDirectory()
        val dest = File(sd, filename)

        val bmImg = BitmapFactory.decodeFile(dest.toString())

        val image = FirebaseVisionImage.fromBitmap(bmImg)

        val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()

        labeler.processImage(image)
            .addOnSuccessListener { labels ->
                var output = "NOT HOTDOG"
                for (label in labels) {
                    val text = label.text
                    if (text == "Hot dog" || text == "hotdog" || text == "Hot Dog" || text == "hot dog" || text == "Hotdog"){
                        output = "HOTDOG"
                    }
                    val entityId = label.entityId
                    val confidence = label.confidence
                }
                Title.text = output
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                Log.d("FIREBASE", "LISTENER FAILED XD")
            }

        backButton.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
