package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.gradeBook.GradeBookDto
import com.example.bsuirmentors.data.remote.dto.omissions.OmissionDto
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOmissionsUseCase @Inject constructor(
    private val repository: IISRepository
) {
    operator fun invoke(): Flow<Resource<OmissionDto>> = flow {
        try {
            emit(Resource.Loading())

            val cookie = repository.getCookie()
            val omissions = repository.getOmissions(cookie)

            emit(Resource.Success(omissions))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}