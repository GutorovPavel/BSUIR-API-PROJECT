package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentWeekUseCase @Inject constructor(
    private val repository: IISRepository
) {
    operator fun invoke(): Flow<Resource<Int>> = flow {
        try {
            emit(Resource.Loading())
            val currentWeek = repository.getCurrentWeek()
            emit(Resource.Success(currentWeek))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}