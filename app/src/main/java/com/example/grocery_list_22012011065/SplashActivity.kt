package com.example.grocery_list_22012011065
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Simple thread-based delay
        Thread {
            Thread.sleep(3000) // 3-second delay
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the SplashActivity
        }.start()
    }
}
