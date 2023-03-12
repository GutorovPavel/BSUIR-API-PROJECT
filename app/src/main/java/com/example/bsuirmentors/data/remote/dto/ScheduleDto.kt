package com.example.bsuirmentors.data.remote.dto


import com.example.bsuirmentors.domain.models.Schedule
import com.google.gson.annotations.SerializedName

data class ScheduleDto(
    val employeeDto: MentorDto,
    val endDate: String,
    val endExamsDate: String,
    val exams: List<Exam>,
    val schedules: Schedules,
    val startDate: String,
    val startExamsDate: String,
    val studentGroupDto: GroupDto
)

fun ScheduleDto.toSchedule(): Schedule {
    return Schedule(schedules, studentGroupDto)
}