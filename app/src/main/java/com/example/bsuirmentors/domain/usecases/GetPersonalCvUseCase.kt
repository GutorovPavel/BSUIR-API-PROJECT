package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
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
            val personalCv = repository.getPersonalCv().toPersonalCv()
            emit(Resource.Success(personalCv))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}