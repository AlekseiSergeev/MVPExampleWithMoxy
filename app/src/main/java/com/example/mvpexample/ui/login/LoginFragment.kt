package com.example.mvpexample.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mvpexample.databinding.FragmentLoginBinding
import com.example.mvpexample.presenter.LoginPresenterImpl


class LoginFragment: Fragment(), LoginView {

    private lateinit var binding: FragmentLoginBinding

    private val loginPresenter = LoginPresenterImpl()

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            email = binding.emailInput.text.toString()
            password = binding.passwordInput.text.toString()

            hideKeyboard()

            loginPresenter.login(email, password)
        }
    }

    override fun showSuccess() {
        hideProgressBar()
        Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        hideProgressBar()
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showEmailError(message: String) {
        binding.emailInputLayout.error = message
    }

    override fun showPasswordError(message: String) {
        binding.passwordInputLayout.error = message
    }

    fun Fragment.hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}