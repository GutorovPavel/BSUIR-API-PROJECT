package com.example.bsuirmentors.domain.models

import com.example.bsuirmentors.data.remote.dto.Exam
import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.Schedules

data class Schedule(
//    val employeeDto: Any,
//    val endDate: String,
//    val endExamsDate: String,
//    val exams: List<Exam>,
    val schedules: Schedules,
//    val startDate: String,
//    val startExamsDate: String,
    val studentGroupDto: GroupDto
)
