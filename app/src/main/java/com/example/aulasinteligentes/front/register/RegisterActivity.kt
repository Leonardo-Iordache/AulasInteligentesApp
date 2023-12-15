package com.example.aulasinteligentes.front.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.conection.ClientServiceImpl
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.RegisterScreenBinding
import com.example.aulasinteligentes.entities.User
import com.example.aulasinteligentes.front.mainscreen.MainScreenActivity
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
                usernameInput = it.nameText.text.toString().trim()
                emailInput = it.idEmail.text.toString().trim()
                passwordInput = it.password.text.toString().trim()
                confirmPasswordInput = it.confirmPassword.text.toString().trim()
            }
            Log.d("RegisterActivityLLL", "FIN DEL REGISTRO")

            completeRegistration(usernameInput, emailInput, passwordInput)
        }
        setContentView(binding.root)
    }

    private fun completeRegistration(userName: String, userEmail: String, userPassword: String) = runBlocking {
        var respuesta = 2
        if ((passwordInput == confirmPasswordInput) && (passwordInput != "")) {
            val user = User(userName, userPassword, userEmail)
            runBlocking {
                job = launch {
                    respuesta = ControllerSingleton.createUser(user)
                    Log.d("RegistroCompletado", "Valor: $respuesta")
                }
            }
            job.invokeOnCompletion {
                if (respuesta == 1) {
                    Log.d("RegistroCompletado", "Se ha completado el registro")
                    val intent = Intent(context, MainScreenActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext, "NOT OLEEE + $respuesta", Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                applicationContext, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT
            ).show()
        }
    }

}