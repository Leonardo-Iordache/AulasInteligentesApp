package com.example.aulasinteligentes.front.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.databinding.MainScreenBinding
import com.example.aulasinteligentes.front.login.LogInActivity
import com.example.aulasinteligentes.front.register.RegisterActivity

class MainScreenActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var binding: MainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        binding = MainScreenBinding.inflate(layoutInflater)

        binding.let{
            loginButton = it.buttonLogin
            registerButton = it.buttonRegister
        }

        loginButton.setOnClickListener{
            Log.d("MainScreenActivity", "Botón de inicio de sesión presionado")
            executeLoginActivity()
        }

        registerButton.setOnClickListener {
            Log.d("MainScreenActivity", "Botón de registro presionado")
            executeRegisterActivity()
        }
        setContentView(binding.root)
    }


    private fun executeLoginActivity(){
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun executeRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }




}