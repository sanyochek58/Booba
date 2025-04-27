package com.example.myapplication

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Model.UserDTOregister
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    lateinit var button: Button;
    lateinit var email_input: EditText;
    lateinit var password_input:EditText;
    lateinit var username_input:EditText;
    lateinit var birthday_input:EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button = findViewById(R.id.REGISTER_BUTTON)
        button.setOnClickListener(this::registerOnButton)

        email_input = findViewById(R.id.EMAIL)
        password_input = findViewById(R.id.PASSWORD)
        username_input = findViewById(R.id.USERNAME)
        birthday_input = findViewById(R.id.BIRTHDAY)

    }

    fun registerOnButton(view : View){
        val text_email = email_input.text.toString()
        val text_password = password_input.text.toString()
        val text_username = username_input.text.toString()
        val text_birthday = birthday_input.text.toString()


        val userDTOregister = UserDTOregister(
            email = text_email,
            password = text_password,
            username = text_username,
            birthOfDate = text_birthday
        )

        RetrofitClient.apiService.register(userDTOregister).enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Ошибка: ${response.errorBody()?.string() ?: "Неизвестная ошибка"}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Ошибка сети: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}