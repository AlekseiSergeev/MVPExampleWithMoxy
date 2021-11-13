package com.example.mvpexample.ui.login

interface LoginView {
    fun showSuccess()
    fun showError(message: String)
    fun showProgressBar()
    fun hideProgressBar()
    fun showEmailError(message: String)
    fun showPasswordError(message: String)
}