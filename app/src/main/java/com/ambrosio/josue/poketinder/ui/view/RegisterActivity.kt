package com.ambrosio.josue.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ambrosio.josue.poketinder.ui.viewmodel.RegisterViewModel
import com.ambrosio.josue.poketinder.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = RegisterViewModel(this)
        observeValues()
    }

    private fun observeValues() {
        registerViewModel.inputsError.observe(this) {
            Toast.makeText(this, "Correo invalido...", Toast.LENGTH_SHORT)
                .show()
        }
        registerViewModel.passwordMismatchError.observe(this) {
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT)
                .show()
        }
        registerViewModel.registrationSuccess.observe(this) {
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            registerViewModel.registerUser(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                confirmPassword = binding.edtPassword2.text.toString()
            )
        }

        binding.btnLoginUser.setOnClickListener {
            Toast.makeText(this, "Ok, regresamos porque ya tienes cuenta", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnBackClose.setOnClickListener {
            Toast.makeText(this, "Ok, regresamos....", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}