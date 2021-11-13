package com.example.mvpexample.presenter


import android.util.Log
import com.example.mvpexample.domain.implemetations.AuthRepositoryImpl
import com.example.mvpexample.ui.login.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class LoginPresenterImpl (): LoginPresenter {

    private val authRepository = AuthRepositoryImpl()
    private var viewState: WeakReference<LoginView>? = null

    fun attachView(view: LoginView) {
        viewState = WeakReference(view)
    }

    override fun login(email: String, password: String) {

        resetErrors()

        if(!validateEmail(email)){
            viewState?.get()?.showEmailError("Enter email")
            return
        }

        if(!validatePassword(password)) {
            viewState?.get()?.showPasswordError("Enter password")
            return
        }

        viewState?.get()?.showProgressBar()

        CoroutineScope(Dispatchers.IO).async {
            val errorMessage = authRepository.login(email,
                password).await()

            if (errorMessage.isEmpty()) {
                launch ( Dispatchers.Main ) {
                    Log.d("LoginPresenter", "Success. Login: $email , password: $password")
                    viewState?.get()?.showSuccess()
                }
            }
            else {
                launch ( Dispatchers.Main ) {
                    Log.d("LoginPresenter", "Error: $errorMessage")
                    viewState?.get()?.showError(errorMessage)
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
        viewState?.get()?.showEmailError("")
        viewState?.get()?.showPasswordError("")
    }
}