package com.rahim.upwork

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UserLoginActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var etUsername : EditText
    lateinit var etPassword : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button
    private var validatePassWord: String? = null
    private var validateUserName: String? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        sharedPreferences = getSharedPreferences(getString(R.string.user_preferences), Context.MODE_PRIVATE)
        validatePassWord = sharedPreferences.getString("UserPassword", "user")
        validateUserName = sharedPreferences.getString("Username","user")
        title = validateUserName
        val loggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(loggedIn)
        {
            val intent = Intent(this@UserLoginActivity,MainInterface::class.java)
            startActivity(intent)
            finish()
        }

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.loginButton)
        signupButton = findViewById(R.id.signupButton)
        loginButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val intent = Intent(this@UserLoginActivity, UserRegistrationActivity::class.java)
        val anotherIntent = Intent(this@UserLoginActivity,MainInterface::class.java)
        when (view.id) {
            R.id.loginButton -> {

                val etUsername = etUsername.text.toString()
                val etPassword = etPassword.text.toString()

                if ( etUsername.isEmpty())
                {
                    Toast.makeText(this@UserLoginActivity, "Please enter the username", Toast.LENGTH_SHORT).show()
                }
                else if ( etPassword.isEmpty())
                {
                    Toast.makeText(this@UserLoginActivity, "Please enter the password", Toast.LENGTH_SHORT).show()
                }
                else if (  etUsername == validateUserName  && etPassword == validatePassWord)
                {
                    title=validateUserName
                    Toast.makeText(this@UserLoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    savePreferences(etUsername)
                    startActivity(anotherIntent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@UserLoginActivity, "Incorrect credentials", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.signupButton -> {
                startActivity(intent)
            }
        }
    }

    private fun areAllFieldsFilled(): Boolean {
          return etUsername.text.isEmpty() && etPassword.text.isEmpty()

    }
    private fun savePreferences(userUsername:String)
    {
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("userUserName",userUsername).apply()
        //sharedPreferences.edit().putString("password",password).apply()

    }
    override fun onPause() {
        super.onPause()
        finish()
    }

}