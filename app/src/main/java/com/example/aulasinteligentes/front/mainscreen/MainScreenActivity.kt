package com.example.aulasinteligentes.front.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.MainScreenBinding
import com.example.aulasinteligentes.entities.User
import com.example.aulasinteligentes.front.register.RegisterActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainScreenActivity : AppCompatActivity() {
    private lateinit var userName: String
    private lateinit var userPassword: String
    private lateinit var userEmail: String
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var binding: MainScreenBinding
    private var job: Job = Job()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        binding = MainScreenBinding.inflate(layoutInflater)

        binding.let{
            userName = it.userName.toString().trim()
            userPassword = it.userPassword.toString().trim()
            userEmail = it.userEmail.toString().trim()
            loginButton = it.buttonLogin
            registerButton = it.buttonRegister
        }

        loginButton.setOnClickListener{
            Log.d("MainScreenActivity", "Botón de inicio de sesión presionado")
            //validateUser(userName, userPassword, userEmail)

        }

        registerButton.setOnClickListener {
            Log.d("MainScreenActivity", "Botón de registro presionado")
            executeRegisterActivity()
        }
        setContentView(binding.root)
    }


    private fun executeMainMenu(){
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
    }

    private fun executeRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    /*private fun validateUser(userName: String, userPassword: String, userEmail: String){
        val user = User(userName, userPassword, userEmail)
        var response = false
        runBlocking {
            job = launch {
                response = ControllerSingleton.validarUsuario(user)
            }
        }
        job.invokeOnCompletion {
            if(response){
                executeMainMenu()
            }
            else{
                Toast.makeText(
                    applicationContext, "Inicio de sesión incorrecto", Toast.LENGTH_SHORT
                ).show()
                Log.i("MainScreenActivity", "Inicio de sesion no valido")
            }
        }
    }*/

}