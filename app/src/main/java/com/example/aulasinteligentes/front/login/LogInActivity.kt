package com.example.aulasinteligentes.front.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.databinding.LoginScreenBinding
import com.example.aulasinteligentes.front.mainscreen.MainMenuActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: LoginScreenBinding
    private lateinit var loginButton: Button
    private lateinit var userName: String
    private lateinit var userPassword: String
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        binding = LoginScreenBinding.inflate(layoutInflater)
        loginButton = binding.logInButtonIniciarSesionActivity

        loginButton.setOnClickListener {
            userName = binding.userName.toString().trim()
            userPassword = binding.userPassword.toString().trim()
            //completeLogin()
            val intent = Intent(this, MainMenuActivity::class.java)
            //intent.putExtra("idUsuario", userLogin.toString())
            startActivity(intent)
        }
        setContentView(binding.root)
    }


    private fun completeLogin(){
        var userLogin = 0
        runBlocking {
            job = launch {
                //TODO:añadir validacion
            }
        }
        job.invokeOnCompletion {
            val intent = Intent(this, MainMenuActivity::class.java)
            //intent.putExtra("idUsuario", userLogin.toString())
            startActivity(intent)
        }
    }











}