package com.example.mvpexample.presentation.view.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface LoginView: MvpView {
    fun showSuccess()
    fun showError(message: String)
    fun showProgressBar()
    fun hideProgressBar()
    fun showEmailError(message: String)
    fun showPasswordError(message: String)
}