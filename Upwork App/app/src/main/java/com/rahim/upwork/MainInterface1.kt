package com.rahim.upwork

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainInterface1 : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnLogout: Button
    private lateinit var vendorSharedPreferences: SharedPreferences
    private var titleNameForVendor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)

        vendorSharedPreferences = getSharedPreferences(getString(R.string.vendor_preferences), Context.MODE_PRIVATE)
        //titleNameForUser = userSharedPreferences.getString("userUserName", "Default User Title")
        titleNameForVendor = vendorSharedPreferences.getString("vendorUserName", " ")

        btnLogout = findViewById(R.id.btnLogout)
        title = titleNameForVendor

        btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this@MainInterface1, MainActivity::class.java)

        val editor = vendorSharedPreferences.edit()
        editor.clear()
        //editor.putBoolean("isLoggedIn", true)
        editor.apply()

        //} else if (title == vendorSharedPreferences.getString("vendorUserName","services")) {
        //   val editor = vendorSharedPreferences.edit()
        // editor.clear()
        //editor.putBoolean("isLoggedIn", true)
        //editor.apply()


        startActivity(intent)
        finish()
    }
}
