package com.example.mvpexample.presentation.presenter.login


import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.mvpexample.domain.implemetations.AuthRepositoryImpl
import com.example.mvpexample.presentation.view.login.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@InjectViewState
class LoginPresenterImpl (private val authRepository: AuthRepositoryImpl): LoginPresenter, MvpPresenter<LoginView>() {

    override fun login(email: String, password: String) {

        resetErrors()

        if(!validateEmail(email)){
            viewState.showEmailError("Enter email")
            return
        }

        if(!validatePassword(password)) {
            viewState.showPasswordError("Enter password")
            return
        }

        viewState.showProgressBar()

        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository.login(email,
                password).await()

            if (errorMessage.isEmpty()) {
                launch ( Dispatchers.Main ) {
                    Log.d("LoginPresenter", "Success. Login: $email , password: $password")
                    viewState.showSuccess()
                }
            }
            else {
                launch ( Dispatchers.Main ) {
                    Log.d("LoginPresenter", "Error: $errorMessage")
                    viewState.showError(errorMessage)
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    private fun resetErrors() {
        viewState.showEmailError("")
        viewState.showPasswordError("")
    }
}