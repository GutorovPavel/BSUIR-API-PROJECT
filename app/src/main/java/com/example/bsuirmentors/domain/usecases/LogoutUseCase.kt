package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.domain.repository.IISRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: IISRepository,
) {
    suspend fun logout(){
        try {
            val cookie = repository.getCookie()
            if (cookie != null) {
                repository.logout(cookie)
            }
            repository.deleteCookie()
            repository.deleteLoginAndPassword()
        } catch (_: Exception) {}
    }
}