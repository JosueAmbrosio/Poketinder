package com.ambrosio.josue.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ambrosio.josue.poketinder.data.database.SharedPreferencesRepository

class LoginViewModel(
    val context: Context
): ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val authError = MutableLiveData<Boolean>()
    val loginSuccess = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }


    fun validateInputs(email: String, password: String) {
        if (isEmptyInputs(email, password)) {
            inputsError.postValue(true)
            return
        }

        val emailSharedPreferences = sharedPreferencesRepository.getUserEmail()
        val passwordPreferences = sharedPreferencesRepository.getUserPassword()

        if (emailSharedPreferences == email && passwordPreferences == password) {
            loginSuccess.postValue(true)
        } else {
            authError.postValue(true)
        }
    }

    private fun isEmptyInputs(email: String, password: String) = email.isEmpty() || password.isEmpty()
}
