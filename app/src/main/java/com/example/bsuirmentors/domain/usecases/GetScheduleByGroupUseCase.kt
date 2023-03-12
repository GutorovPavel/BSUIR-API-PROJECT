package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.*
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Schedule
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScheduleByGroupUseCase @Inject constructor(
    private val repository: IISRepository
) {
//    operator fun invoke(studentGroup: String): Flow<Resource<Schedule>> = flow {
//        try {
//            emit(Resource.Loading())
//            val schedule = repository.getScheduleByGroup(studentGroup).toSchedule()
//            emit(Resource.Success(schedule))
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
//        } catch (e: IOException) {
//            emit(Resource.Error("Check ur internet connection :("))
//        }
//    }
    operator fun invoke(studentGroup: String): Flow<Resource<Schedule>> = flow {
        try {
            emit(Resource.Loading())
            val schedule = repository.getScheduleByGroup(studentGroup).toSchedule()
            emit(Resource.Success(schedule))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}