package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.profile.PersonalCvDto
import com.example.bsuirmentors.data.remote.dto.profile.toPersonalCv
import com.example.bsuirmentors.domain.models.PersonalCv
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPersonalCvUseCase @Inject constructor(
    private val repository: IISRepository
) {
    operator fun invoke(): Flow<Resource<PersonalCv>> = flow {
        try {
            emit(Resource.Loading())

            val cookie = repository.getCookie()
            val personalCv = repository.getPersonalCv(cookie).toPersonalCv()

            emit(Resource.Success(personalCv))

        } catch (e: HttpException) {
            val loginRequest = repository.getLoginAndPassword()

            if (loginRequest?.username == null || loginRequest.password == null) {
                emit(Resource.Error("no cookie"))
            } else {
                try {
                    repository.login(LoginRequest(loginRequest.username, loginRequest.password))

                    val cookie = repository.getCookie()
                    val personalCv = repository.getPersonalCv(cookie).toPersonalCv()

                    emit(Resource.Success(personalCv))

                } catch (e: HttpException) {
                    emit(Resource.Error(e.localizedMessage ?: "HTTP Error"))
                } catch (e: IOException) {
                    emit(Resource.Error(e.localizedMessage?: "IO Error"))
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}