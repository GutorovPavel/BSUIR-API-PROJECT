package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.toGroup
import com.example.bsuirmentors.data.remote.dto.toMentor
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllGroupsUseCase @Inject constructor(
    private val repository: IISRepository
) {
    operator fun invoke(): Flow<Resource<List<Group>>> = flow {
        try {
            emit(Resource.Loading())
            val groups = repository.getGroups().map {it.toGroup()}
            emit(Resource.Success(groups))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }
    }
}