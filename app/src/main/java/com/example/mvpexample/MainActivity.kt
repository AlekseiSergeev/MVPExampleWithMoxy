package com.example.mvpexample

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.mvpexample.ui.login.LoginFragment

class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commitNow()
        }
    }
}