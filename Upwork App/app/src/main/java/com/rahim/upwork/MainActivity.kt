package com.rahim.upwork

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.upwork_interface)
        title = "Upwork"

        val vendorButton: Button = findViewById(R.id.vendorButton)
        val userButton: Button = findViewById(R.id.userButton)

        vendorButton.setOnClickListener(this)


        userButton.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.vendorButton -> {
                val intent = Intent(this@MainActivity, VendorLoginActivity::class.java)
                startActivity(intent)
            }
            R.id.userButton -> {
                val intent = Intent(this@MainActivity, UserLoginActivity::class.java)
                startActivity(intent)
            }
            // Add more cases for other buttons if needed
        }
    }
}