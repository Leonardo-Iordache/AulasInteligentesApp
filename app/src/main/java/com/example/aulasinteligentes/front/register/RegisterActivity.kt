package com.example.aulasinteligentes.front.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.databinding.RegisterScreenBinding
import com.example.aulasinteligentes.front.login.LogInActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: RegisterScreenBinding
    private lateinit var returnButton: Button
    private lateinit var confirmButton: Button
    private lateinit var usernameInput: String
    private lateinit var emailInput: String
    private lateinit var passwordInput: String
    private lateinit var confirmPasswordInput: String
    private var job: Job = Job()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)
        binding = RegisterScreenBinding.inflate(layoutInflater)

        binding.let{
            returnButton = it.returnButton
            confirmButton = it.confirmButton
        }

        returnButton.setOnClickListener {
            finish()
        }

        confirmButton.setOnClickListener {
            binding.let{
                usernameInput = it.nameText.toString().trim()
                emailInput = it.idEmail.toString().trim()
                passwordInput = it.password.text.toString().trim()
                confirmPasswordInput = it.confirmPassword.text.toString().trim()
            }
            completeRegistration()
            Log.d("RegisterActivity", "FIN DEL REGISTRO")
        }
        setContentView(binding.root)
    }

    private fun completeRegistration() = runBlocking {
        if ((passwordInput == confirmPasswordInput) && (passwordInput != "")) {
            job = launch {
                //TODO: llamar a añadir usuario
            }
            job.join()
            val intent = Intent(context, LogInActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                applicationContext, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT
            ).show()
        }
    }

}