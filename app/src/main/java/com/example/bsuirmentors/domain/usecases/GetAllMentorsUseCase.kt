package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.local.entities.toMentor
import com.example.bsuirmentors.data.local.entities.toMentorEntity
import com.example.bsuirmentors.data.remote.dto.toGroup
import com.example.bsuirmentors.data.remote.dto.toMentor
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMentorsUseCase @Inject constructor(
    private val repository: IISRepository
) {
    operator fun invoke(): Flow<Resource<List<Mentor>>> = flow {

        try {
            emit(Resource.Loading())
            val remoteMentors = repository.getMentors().map { it.toMentor() }
            repository.insertMentorsToLocal(remoteMentors.map { it.toMentorEntity() })

            val localMentors = repository.getMentorsFromLocal().map { it.toMentor() }
            emit(Resource.Success(localMentors))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))

        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
            try {
                emit(Resource.Loading())

                val localMentors = repository.getMentorsFromLocal().map { it.toMentor() }

                if (localMentors.isNotEmpty()) {
                    emit(Resource.Success(localMentors))
                }
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage?: "Something went wrong.."))
            }
        }
    }
}