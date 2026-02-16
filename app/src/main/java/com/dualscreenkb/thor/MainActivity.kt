package com.dualscreenkb.thor

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Minimal layout built programmatically to keep example small
        val btn = Button(this)
        btn.text = "Open Input Method Settings"
        btn.setOnClickListener {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }
        setContentView(btn)
    }
}
