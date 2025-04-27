package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Model.UserDTOlogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var button_login: Button
    lateinit var button_register: Button
    lateinit var input_email: EditText
    lateinit var input_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button_login = findViewById(R.id.LOGIN)
        button_login.setOnClickListener(this::onClickSignIn)

        button_register = findViewById(R.id.REGISTER)
        button_register.setOnClickListener(this::onClickSignUp)

        input_email = findViewById(R.id.EMAIL)
        input_password = findViewById(R.id.PASSWORD)
    }

    fun onClickSignIn(view: View) {
        val text_login: String = input_email.text.toString()
        val text_password: String = input_password.text.toString()
        val intent: Intent = Intent(this,MainContentActivity::class.java);

        val userDTOlogin = UserDTOlogin(
            email = text_login,
            password = text_password
        )

        RetrofitClient.apiService.authenticate(userDTOlogin).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val jwtToken = response.body()
                    Toast.makeText(this@MainActivity,"Succeed auth",Toast.LENGTH_SHORT).show()
                    println("JWT Token: $jwtToken")
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity,"Failed auth",Toast.LENGTH_SHORT).show()
                    println("Authentication failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    fun onClickSignUp(view: View){
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}