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

class VendorLoginActivity : AppCompatActivity(),View.OnClickListener{


    lateinit var etEmail : EditText
    lateinit var etId : EditText
    private lateinit var etPassword : EditText
    lateinit var loginButton : Button
    lateinit var signupButton : Button
    lateinit var sharedPreferences : SharedPreferences
    private var validateName: String? = null
    private var validateEmail: String? = null
    private var validatePassword: String? = null

    //val validateEtName = "vendor"
    //val validateEtEmail = "vendor"
    private val validateId = "1234vendor"
   // val validateEtPassword = "vendor"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_login)
        sharedPreferences = getSharedPreferences(getString(R.string.vendor_preferences), Context.MODE_PRIVATE)
        validateName = sharedPreferences.getString("Name","vendor")
        //val validateCompany = sharedPreferences.getString("Company","RHRITSolutions")
        validateEmail = sharedPreferences.getString("Email","vendor123@gmail.com")
        validatePassword = sharedPreferences.getString("Password","vendor")
        val loggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(loggedIn)
        {
            val intent = Intent(this@VendorLoginActivity,MainInterface1::class.java)
            startActivity(intent)
            finish()
        }
        title=validateName
        etEmail = findViewById(R.id.etEmail)
        etId = findViewById(R.id.etId)
        etPassword = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.loginButton)
        signupButton = findViewById(R.id.signupButton)
        loginButton.setOnClickListener(this)
        signupButton.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val intent = Intent(this@VendorLoginActivity, VendorRegistrationActivity::class.java)
        val anotherIntent = Intent(this@VendorLoginActivity,MainInterface1::class.java)
        when (view.id) {
            R.id.loginButton -> {
                val  etEmail = etEmail.text.toString()
                val  etId = etId.text.toString()
                val etPassword = etPassword.text.toString()

                if ( etEmail.isEmpty())
                {
                    Toast.makeText(this@VendorLoginActivity, "Please enter the email", Toast.LENGTH_SHORT).show()
                }
                else if ( etId.isEmpty())
                {
                    Toast.makeText(this@VendorLoginActivity, "Please enter the Id", Toast.LENGTH_SHORT).show()
                }
                else if ( etPassword.isEmpty())
                {
                    Toast.makeText(this@VendorLoginActivity, "Please enter the password", Toast.LENGTH_SHORT).show()
                }
                else if ( etEmail == validateEmail && etId == validateId && etPassword == validatePassword )
                {
                    Toast.makeText(this@VendorLoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    savePreferences(validateName)
                    startActivity(anotherIntent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@VendorLoginActivity, "Incorrect credentials", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.signupButton -> {
                startActivity(intent)
            }
        }
    }

    //private fun areAllFieldsFilledOrNot(): Boolean {
        //return etName.text.isEmpty() || etEmail.text.isEmpty() || etId.text.isEmpty() || etPassword.text.isEmpty()
    //}

    private fun areAllFieldsFilled(): Boolean {
        return  etEmail.text.isEmpty() && etId.text.isEmpty() && etPassword.text.isEmpty()
    }

    private fun savePreferences(vendorUserName:String?)
    {
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("vendorUserName",vendorUserName).apply()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}