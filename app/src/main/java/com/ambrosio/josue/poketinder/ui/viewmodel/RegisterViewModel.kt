package com.ambrosio.josue.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ambrosio.josue.poketinder.data.database.SharedPreferencesRepository

class RegisterViewModel(
    val context: Context
): ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val passwordMismatchError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (validateInputs(email, password, confirmPassword)) {
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            registrationSuccess.postValue(true)
        }
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        if (isEmptyInputs(email, password, confirmPassword)) {
            inputsError.postValue(true)
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputsError.postValue(true)
            return false
        }

        if (password != confirmPassword) {
            passwordMismatchError.postValue(true)
            return false
        }

        return true
    }

    private fun isEmptyInputs(email: String, password: String, confirmPassword: String) = email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
}
