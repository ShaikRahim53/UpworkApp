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

class UserRegistrationActivity : AppCompatActivity(),View.OnClickListener {
      lateinit var etEmail : EditText
      lateinit var etUsername : EditText
      lateinit var etPassword : EditText
      lateinit var etConfirmPassword : EditText
      lateinit var sharedPreferences : SharedPreferences
      lateinit var loginButton : Button
      lateinit var signupButton: Button

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)
        sharedPreferences = getSharedPreferences(getString(R.string.user_preferences), Context.MODE_PRIVATE)
        title="User Registration"

        etEmail = findViewById(R.id.etEmail)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etCconfirmPassword)
        signupButton = findViewById(R.id.signupButton)
        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = Intent(this@UserRegistrationActivity, UserLoginActivity::class.java)
        val  etEmail = etEmail.text.toString()
        val  etUsername = etUsername.text.toString()
        val etPassword = etPassword.text.toString()
        val etConfirmPassword = etConfirmPassword.text.toString()
        when (view.id) {
            R.id.signupButton -> {
                    if ( etEmail.isEmpty())
                    {
                        Toast.makeText(this@UserRegistrationActivity, "please enter the email", Toast.LENGTH_SHORT).show()
                    }
                    else if ( !etEmail.contains("@gmail.com"))
                    {
                        Toast.makeText(this@UserRegistrationActivity, "This email is not valid", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etUsername.isEmpty())
                    {
                         Toast.makeText(this@UserRegistrationActivity, "please enter the username", Toast.LENGTH_SHORT).show()
                    }
                    else if (etUsername.length !in 5..15)
                    {
                        Toast.makeText(this@UserRegistrationActivity, "please create a proper username", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etPassword.isEmpty())
                    {
                    Toast.makeText(this@UserRegistrationActivity, "please enter the password", Toast.LENGTH_SHORT).show()
                    }
                    else if ( !containsUppercase(etPassword))
                    {
                        Toast.makeText(this@UserRegistrationActivity, "Password needs 1 Uppercase letter minimum", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@UserRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( !containsNumber(etPassword))
                    {
                        Toast.makeText(this@UserRegistrationActivity, "Password needs at least 1 number", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@UserRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( !containsSpecialChar(etPassword))
                    {
                        Toast.makeText(this@UserRegistrationActivity, "Password needs 1 special character minimum", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@UserRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( etPassword.length !in 8..12 )
                    {

                        Toast.makeText(this@UserRegistrationActivity, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etConfirmPassword.isEmpty())
                    {
                        Toast.makeText(this@UserRegistrationActivity, "please enter the confirm password ", Toast.LENGTH_SHORT).show()
                    }
                    else if( etPassword != etConfirmPassword ) {
                        Toast.makeText(this@UserRegistrationActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etEmail.isNotEmpty() && etUsername.isNotEmpty() && etPassword.isNotEmpty() && etConfirmPassword.isNotEmpty()){
                        Toast.makeText(
                            this@UserRegistrationActivity,
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        savePreferences(etUsername,etPassword)
                        startActivity(intent)
                    }
                }
            R.id.loginButton ->{
                startActivity(intent)
            }
            }

    }

    //private fun areAllFieldsFilled(): Boolean {
      //  return etEmail.text.isNotEmpty() && etUsername.text.isNotEmpty() && etPassword.text.isNotEmpty() && etConfirmPassword.text.isNotEmpty()
    //}

    private fun savePreferences(Username:String?,userPassword:String?)
    {
        sharedPreferences.edit().putString("Username",Username).apply()
        sharedPreferences.edit().putString("UserPassword",userPassword).apply()

    }
    private fun containsUppercase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }
    private fun containsSpecialChar(password: String): Boolean {
        val specialCharacterRegex = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")

        return specialCharacterRegex.containsMatchIn(password)
    }
    private fun containsNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}