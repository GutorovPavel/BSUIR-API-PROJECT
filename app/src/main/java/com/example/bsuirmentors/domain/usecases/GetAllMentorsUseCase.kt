package com.example.bsuirmentors.domain.usecases

import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.toMentor
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.repository.MentorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMentorsUseCase @Inject constructor(
    private val repository: MentorRepository
) {
    operator fun invoke(): Flow<Resource<List<Mentor>>> = flow {
        try {
            emit(Resource.Loading())
            val mentors = repository.getMentors().map {it.toMentor()}
            emit(Resource.Success(mentors))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred..."))
        } catch (e: IOException) {
            emit(Resource.Error("Check ur internet connection :("))
        }

    }
}