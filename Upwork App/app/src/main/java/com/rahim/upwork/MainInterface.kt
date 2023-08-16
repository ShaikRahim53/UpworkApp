package com.rahim.upwork

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainInterface : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnLogout: Button
    private lateinit var userSharedPreferences: SharedPreferences
    private var titleNameForUser: String? = null
    private var titleNameForVendor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_interface)

        userSharedPreferences = getSharedPreferences(getString(R.string.user_preferences), Context.MODE_PRIVATE)
        titleNameForUser = userSharedPreferences.getString("userUserName"," ")
        //titleNameForVendor = vendorSharedPreferences.getString("vendorUserName", "Default Vendor Title")

        btnLogout = findViewById(R.id.btnLogout)
        title = titleNameForUser

        btnLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this@MainInterface, MainActivity::class.java)

            //titluserSharedPreferences.getString("userUserName","services")) {
            val editor = userSharedPreferences.edit()
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
