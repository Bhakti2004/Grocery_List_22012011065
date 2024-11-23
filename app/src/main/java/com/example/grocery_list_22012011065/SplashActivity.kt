package com.example.grocery_list_22012011065

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {

    private lateinit var taglineTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize the TextView
        taglineTextView = findViewById(R.id.taglineTextView)

        // List of taglines
        val taglines = listOf(
            "Organize Your Grocery Hassle-Free!",
            "Save Time, Save Money!",
            "Your Smart Grocery Helper!",
            "Simplify Your Grocery Shopping!",
            "The Grocery App You Can Count On!"
        )

        // Pick a random tagline
        val randomTagline = taglines[Random.nextInt(taglines.size)]
        taglineTextView.text = randomTagline

        // Simple thread-based delay
        Thread {
            Thread.sleep(2000) // 3-second delay
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the SplashActivity
        }.start()
    }
}
