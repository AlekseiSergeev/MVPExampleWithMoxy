package com.example.mvpexample.domain.implemetations

import com.example.mvpexample.domain.models.AuthRepository
import kotlinx.coroutines.*


class AuthRepositoryImpl: AuthRepository {
    override suspend fun login(email: String, password: String): Deferred<String> {
       return CoroutineScope(Dispatchers.IO).async {
            delay(3000)
             ""
        }
    }
}