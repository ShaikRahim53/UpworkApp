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
import kotlin.math.E

class VendorRegistrationActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var etName : EditText
    lateinit var etCompany : EditText
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var loginButton: Button
    lateinit var signupButton: Button
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_registration)
        sharedPreferences = getSharedPreferences(getString(R.string.vendor_preferences), Context.MODE_PRIVATE)

        title = "vendor registration"

        etName= findViewById(R.id.etName)
        etCompany = findViewById(R.id.etCompany)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        signupButton = findViewById(R.id.signupButton)
        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = Intent(this@VendorRegistrationActivity, VendorLoginActivity::class.java)
        val  etName = etName.text.toString()
        val  etCompany = etCompany.text.toString()
        val  etEmail = etEmail.text.toString()
        val etPassword = etPassword.text.toString()
        when (view.id) {
            R.id.signupButton -> {
                    if ( etName.isEmpty())
                    {
                      Toast.makeText(this@VendorRegistrationActivity, "please enter the full name", Toast.LENGTH_SHORT).show()
                    }
                    else if ( validNameOrNot(etName))
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "please enter a proper name", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etCompany.isEmpty())
                    {
                     Toast.makeText(this@VendorRegistrationActivity, "please enter the company name", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etCompany.length !in 3..20)
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "please enter a valid company name", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etEmail.isEmpty())
                    {
                     Toast.makeText(this@VendorRegistrationActivity, "please enter the email", Toast.LENGTH_SHORT).show()
                    }
                    else if ( !etEmail.contains("@gmail.com"))
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "This email is not valid", Toast.LENGTH_SHORT).show()
                    }
                    else if ( etPassword.isEmpty())
                    {
                      Toast.makeText(this@VendorRegistrationActivity, "please enter the password", Toast.LENGTH_SHORT).show()
                    }
                    else if ( !containsUppercase(etPassword))
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "Password needs 1 Uppercase letter minimum", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@VendorRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( !containsNumber(etPassword))
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "Password needs at least 1 number", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@VendorRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( !containsSpecialChar(etPassword))
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "Password needs 1 special character minimum", Toast.LENGTH_SHORT).show()
                        if ( etPassword.length == 12)
                        {
                            Toast.makeText(this@VendorRegistrationActivity, "Password max 12 characters", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else if ( etPassword.length !in 8..12 )
                    {
                        Toast.makeText(this@VendorRegistrationActivity, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                    }
                    else if( etName.isNotEmpty() && etCompany.isNotEmpty() && etEmail.isNotEmpty() && etPassword.isNotEmpty()) {
                        Toast.makeText(
                            this@VendorRegistrationActivity,
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        savePreferences(etName, etEmail, etPassword)
                        startActivity(intent)
                    }

            }
            R.id.loginButton -> {
                startActivity(intent)
            }
        }
    }

   // private fun areAllFieldsFilled(): Boolean {
     //   return etName.text.isNotEmpty() && etCompany.text.isNotEmpty() && etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()
    //}

    private fun savePreferences(Name:String?,Email:String?,Password:String?)
    {
        sharedPreferences.edit().putString("Name",Name).apply()
        sharedPreferences.edit().putString("Email",Email).apply()
        sharedPreferences.edit().putString("Password",Password).apply()

    }
    private fun containsUppercase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }
    private fun containsSpecialChar(password: String): Boolean {
        val specialCharacterRegex = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")

        return specialCharacterRegex.containsMatchIn(password)
    }
    private fun validNameOrNot(name: String): Boolean {
        val specialCharacterRegex = Regex("[\\\\!@#<&>*%\\-\$^\\[\\]()'_\"+/]")

        return specialCharacterRegex.containsMatchIn(name)
    }
    private fun containsNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}